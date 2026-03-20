package marcelo.project.pricefy.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import marcelo.project.pricefy.dto.request.UserRequestDto;
import marcelo.project.pricefy.dto.response.UserResponseDto;
import marcelo.project.pricefy.entity.UserModel;
import marcelo.project.pricefy.repository.UserRepository;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponseDto createUser(UserRequestDto userRequest){
        UserModel user = new UserModel();

        user.setIdUser(userRequest.idUser());
        user.setDsUsername(userRequest.dsUsername());
        user.setDsEmail(userRequest.dsEmail());
        user.setDsPassword(userRequest.dsPassword());

        UserModel userSaved = userRepository.save(user);

        return new UserResponseDto(
                userSaved.getIdUser(),
                userSaved.getDsUsername(),
                userSaved.getDsEmail(),
                userSaved.getDsPassword()
        );
    }
}
