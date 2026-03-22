package marcelo.project.pricefy.repository;

import marcelo.project.pricefy.entity.MarketModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MarketRepository extends JpaRepository<MarketModel, Long> {

    Optional<MarketModel> findByIdMarketAndUser_IdUser (Long idMarket, Long idUser);

    List<MarketModel> findAllByUser_IdUser (Long idUser);

    void deleteAllByUser_IdUser(Long idUser);
}
