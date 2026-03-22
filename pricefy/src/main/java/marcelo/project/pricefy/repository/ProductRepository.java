package marcelo.project.pricefy.repository;

import marcelo.project.pricefy.entity.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {

    List<ProductModel> findAllByUser_IdUser (Long idUser);
    }
