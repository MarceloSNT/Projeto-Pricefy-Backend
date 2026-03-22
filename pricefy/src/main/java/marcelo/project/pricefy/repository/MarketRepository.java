package marcelo.project.pricefy.repository;

import marcelo.project.pricefy.entity.MarketModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarketRepository extends JpaRepository<MarketModel, Long> {

    MarketModel findByIdMarket (Long idMarket);

    List<MarketModel> findAllByUser_IdUser (Long idUser);
}
