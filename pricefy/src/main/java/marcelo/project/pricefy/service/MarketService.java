package marcelo.project.pricefy.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import marcelo.project.pricefy.dto.request.market.MarketRequestDto;
import marcelo.project.pricefy.dto.response.market.MarketResponseDto;
import marcelo.project.pricefy.entity.MarketModel;
import marcelo.project.pricefy.entity.UserModel;
import marcelo.project.pricefy.repository.MarketRepository;
import marcelo.project.pricefy.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class MarketService {

    private final MarketRepository marketRepository;
    private final UserRepository userRepository;

    @Transactional
    public MarketResponseDto createMarket(Long idUser, MarketRequestDto marketRequestDto){
        UserModel user  = userRepository.findByIdUser(idUser);
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
    public List<MarketModel> listingAllByUser(Long idUser){
        return marketRepository.findAllByUser_IdUser(idUser);
    }
}
