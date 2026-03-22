package marcelo.project.pricefy.repository;

import marcelo.project.pricefy.entity.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {
}
