package marcelo.project.pricefy.repository;

import marcelo.project.pricefy.entity.MarketModel;
import marcelo.project.pricefy.entity.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {

    List<ProductModel> findAllByUser_IdUser (Long idUser);

    Optional<ProductModel> findByIdProductAndUser_IdUser (Long idProduct, Long idUser);

    void deleteAllByUser_idUser (Long idUser);

}
