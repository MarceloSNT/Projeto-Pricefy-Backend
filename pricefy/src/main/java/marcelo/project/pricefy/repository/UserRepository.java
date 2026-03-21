package marcelo.project.pricefy.repository;

import marcelo.project.pricefy.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    UserModel findByIdUser(Long idUser);

    UserModel findByDsUsername(String username);
}
