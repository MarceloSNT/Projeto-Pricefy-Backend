package marcelo.project.pricefy.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import marcelo.project.pricefy.dto.request.product.ProductRequestDto;
import marcelo.project.pricefy.dto.response.product.ProductResponseDto;
import marcelo.project.pricefy.entity.ProductModel;
import marcelo.project.pricefy.entity.UserModel;
import marcelo.project.pricefy.repository.ProductRepository;
import marcelo.project.pricefy.repository.UserRepository;
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
}
