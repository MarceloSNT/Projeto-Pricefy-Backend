package marcelo.project.pricefy.repository;

import marcelo.project.pricefy.entity.PriceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<PriceModel, Long> {

}
