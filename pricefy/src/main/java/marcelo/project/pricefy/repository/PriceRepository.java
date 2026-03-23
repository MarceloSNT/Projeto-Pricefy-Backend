package marcelo.project.pricefy.repository;

import marcelo.project.pricefy.entity.PriceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<PriceModel, Long> {

    List<PriceModel> findAllByUser_IdUser (Long idUser);

}
