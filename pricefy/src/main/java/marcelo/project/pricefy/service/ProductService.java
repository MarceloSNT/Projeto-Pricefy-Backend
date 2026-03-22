package marcelo.project.pricefy.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import marcelo.project.pricefy.dto.request.product.ProductRequestDto;
import marcelo.project.pricefy.dto.request.product.ProductRequestEditDto;
import marcelo.project.pricefy.dto.response.product.ProductResponseDto;
import marcelo.project.pricefy.entity.MarketModel;
import marcelo.project.pricefy.entity.ProductModel;
import marcelo.project.pricefy.entity.UserModel;
import marcelo.project.pricefy.repository.ProductRepository;
import marcelo.project.pricefy.repository.UserRepository;
import marcelo.project.pricefy.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Transactional
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto, Long idUser){
        UserModel user = userRepository.findByIdUser(idUser);
        ProductModel product = new ProductModel();

        product.setIdProduct(productRequestDto.idProduct());
        product.setDsName(productRequestDto.dsName());
        product.setUser(user);

        ProductModel productSaved = productRepository.save(product);

        return new ProductResponseDto(
                productSaved.getIdProduct(),
                productSaved.getDsName(),
                productSaved.getUser().getDsUsername()
        );
    }

    @Transactional
    public List<ProductModel> listAllByUser(Long idUser){
        return productRepository.findAllByUser_IdUser(idUser);
    }

    @Transactional
    public ProductResponseDto editProduct(Long idProduct, Long idUser, ProductRequestEditDto productRequestEditDto){
        ProductModel product = productRepository
                .findByIdProductAndUser_IdUser(idProduct, idUser)
                .orElseThrow(() -> new RuntimeException("Produto nãp encontrado ou sem permissão"));

        Utils.copyNonNullProperties(productRequestEditDto, product);

        ProductModel productEdit = productRepository.save(product);
        return new ProductResponseDto(
                productEdit.getIdProduct(),
                productEdit.getDsName(),
                productEdit.getUser().getDsUsername()
        );
    }

    @Transactional
    public void deleteProduct(Long idProduct, Long idUser){
        ProductModel product = productRepository.findByIdProductAndUser_IdUser(idProduct, idUser)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado ou sem permissão"));

        productRepository.delete(product);
    }
}
