package marcelo.project.pricefy.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import marcelo.project.pricefy.dto.request.price.PriceRequestDto;
import marcelo.project.pricefy.dto.request.price.PriceRequestEditDto;
import marcelo.project.pricefy.dto.response.price.PriceResponseDto;
import marcelo.project.pricefy.entity.MarketModel;
import marcelo.project.pricefy.entity.PriceModel;
import marcelo.project.pricefy.entity.ProductModel;
import marcelo.project.pricefy.entity.UserModel;
import marcelo.project.pricefy.repository.MarketRepository;
import marcelo.project.pricefy.repository.PriceRepository;
import marcelo.project.pricefy.repository.ProductRepository;
import marcelo.project.pricefy.repository.UserRepository;
import marcelo.project.pricefy.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PriceService {

    private final PriceRepository priceRepository;
    private final ProductRepository productRepository;
    private final MarketRepository marketRepository;
    private final UserRepository userRepository;

    @Transactional
    public PriceResponseDto createPrice(PriceRequestDto priceRequestDto, Long idUser){
        ProductModel product = productRepository.findById(priceRequestDto.product())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        MarketModel market = marketRepository.findById(priceRequestDto.market())
                .orElseThrow(() -> new RuntimeException("Mercado não encontrado"));

        UserModel user = userRepository.findByIdUser(idUser);

        PriceModel price = new PriceModel();

        price.setIdPrice(priceRequestDto.idPrice());
        price.setVlProduct(priceRequestDto.vlProduto());
        price.setProduct(product);
        price.setMarket(market);
        price.setUser(user);

        PriceModel priceSaved = priceRepository.save(price);

        return new PriceResponseDto(
                priceSaved.getIdPrice(),
                priceSaved.getVlProduct(),
                priceSaved.getProduct().getDsName(),
                priceSaved.getMarket().getDsName(),
                priceSaved.getUser().getDsUsername()
        );
    }

    @Transactional
    public List<PriceModel> listingAll(Long idUser){
        return priceRepository.findAllByUser_IdUser(idUser);
    }

    @Transactional
    public PriceResponseDto edit(PriceRequestEditDto priceRequestEditDto, Long idUser, Long idPrice){
        PriceModel price = priceRepository.findByIdPriceAndUser_IdUser(idPrice, idUser).orElseThrow(() -> new RuntimeException("Preço não encontrado"));

        Utils.copyNonNullProperties(priceRequestEditDto, price);

        PriceModel priceEdit = priceRepository.save(price);
        return new PriceResponseDto(
                priceEdit.getIdPrice(),
                priceEdit.getVlProduct(),
                priceEdit.getProduct().getDsName(),
                priceEdit.getMarket().getDsName(),
                priceEdit.getUser().getDsUsername()
        );
    }

    @Transactional
    public void deletePrice(Long idUser, Long idPrice){
        PriceModel price = priceRepository.findByIdPriceAndUser_IdUser(idPrice, idUser).orElseThrow(() -> new RuntimeException("Preço não encontrado ou sem permissão " + idPrice));

        priceRepository.delete(price);
    }

    @Transactional
    public void deleteAllPrice(Long idUser){
        priceRepository.deleteAllByUser_IdUser(idUser);
    }
}
