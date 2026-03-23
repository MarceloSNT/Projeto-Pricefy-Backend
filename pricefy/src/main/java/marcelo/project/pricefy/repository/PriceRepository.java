package marcelo.project.pricefy.repository;

import marcelo.project.pricefy.entity.PriceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<PriceModel, Long> {

    List<PriceModel> findAllByUser_IdUser (Long idUser);

    Optional<PriceModel> findByIdPriceAndUser_IdUser (Long idPrice, Long idUser);

    void deleteAllByUser_IdUser (Long idUser);
}
