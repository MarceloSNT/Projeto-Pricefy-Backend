package marcelo.project.pricefy.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import marcelo.project.pricefy.dto.request.market.MarketRequestDto;
import marcelo.project.pricefy.dto.response.market.MarketResponseDto;
import marcelo.project.pricefy.entity.MarketModel;
import marcelo.project.pricefy.repository.MarketRepository;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MarketService {

    private final MarketRepository marketRepository;

    @Transactional
    public MarketResponseDto createMarket(MarketRequestDto marketRequestDto){
        MarketModel market = new MarketModel();

        market.setIdMarket(marketRequestDto.idMarket());
        market.setDsName(marketRequestDto.dsName());

        MarketModel marketSaved = marketRepository.save(market);

        return new MarketResponseDto(
                marketSaved.getIdMarket(),
                marketSaved.getDsName()
        );
    }
}
