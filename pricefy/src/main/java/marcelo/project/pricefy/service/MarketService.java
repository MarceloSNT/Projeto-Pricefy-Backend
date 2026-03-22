package marcelo.project.pricefy.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import marcelo.project.pricefy.dto.request.market.MarketRequestDto;
import marcelo.project.pricefy.dto.request.market.MarketRequestEditDto;
import marcelo.project.pricefy.dto.response.market.MarketResponseDto;
import marcelo.project.pricefy.entity.MarketModel;
import marcelo.project.pricefy.entity.UserModel;
import marcelo.project.pricefy.repository.MarketRepository;
import marcelo.project.pricefy.repository.UserRepository;
import marcelo.project.pricefy.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class MarketService {

    private final MarketRepository marketRepository;
    private final UserRepository userRepository;

    @Transactional
    public MarketResponseDto createMarket(Long idUser, MarketRequestDto marketRequestDto) {
        UserModel user = userRepository.findByIdUser(idUser);
        MarketModel market = new MarketModel();

        market.setIdMarket(marketRequestDto.idMarket());
        market.setDsName(marketRequestDto.dsName());
        market.setUser(user);

        MarketModel marketSaved = marketRepository.save(market);

        return new MarketResponseDto(
                marketSaved.getIdMarket(),
                marketSaved.getDsName(),
                marketSaved.getUser().getDsUsername()
        );
    }

    @Transactional
    public List<MarketModel> listingAllByUser(Long idUser) {
        return marketRepository.findAllByUser_IdUser(idUser);
    }

    @Transactional
    public MarketResponseDto editMarket(Long idMarket, Long idUser, MarketRequestEditDto marketRequestDto) {
        MarketModel market = marketRepository
                .findByIdMarketAndUser_IdUser(idMarket, idUser)
                .orElseThrow(() -> new RuntimeException("Mercado não encontrado ou sem permissão"));

        Utils.copyNonNullProperties(marketRequestDto, market);

        MarketModel marketEdit = marketRepository.save(market);
        return new MarketResponseDto(
                marketEdit.getIdMarket(),
                marketEdit.getDsName(),
                marketEdit.getUser().getDsUsername()
        );
    }

    @Transactional
    public void deleteByUser(Long idMarket, Long idUser) {
        MarketModel market = marketRepository.findByIdMarketAndUser_IdUser(idMarket, idUser).orElseThrow(() -> new RuntimeException("Mercado não encontrado ou sem permissão"));

        marketRepository.delete(market);
    }

    @Transactional
    public void deleteAllByUser(Long idUser) {
         marketRepository.deleteAllByUser_IdUser(idUser);
    }
}
