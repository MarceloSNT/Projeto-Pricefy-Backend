package marcelo.project.pricefy.repository;

import marcelo.project.pricefy.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findByIdUser(Long idUser);

    UserModel findByDsUsername(String username);
}
