package marcelo.project.pricefy.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import marcelo.project.pricefy.utils.Utils;
import marcelo.project.pricefy.dto.request.user.UserRequestDto;
import marcelo.project.pricefy.dto.request.user.UserRequestEditDto;
import marcelo.project.pricefy.dto.response.UserResponseDto;
import marcelo.project.pricefy.entity.UserModel;
import marcelo.project.pricefy.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponseDto createUser(UserRequestDto userRequest){
        UserModel user = new UserModel();

        user.setIdUser(userRequest.idUser());
        user.setDsUsername(userRequest.dsUsername());
        user.setDsEmail(userRequest.dsEmail());
        user.setDsPassword(passwordEncoder.encode(userRequest.dsPassword()));

        UserModel userSaved = userRepository.save(user);

        return new UserResponseDto(
                userSaved.getIdUser(),
                userSaved.getDsUsername(),
                userSaved.getDsEmail(),
                userSaved.getDsPassword()
        );
    }

    @Transactional
    public UserModel listById(Long id) {
        return userRepository.findByIdUser(id);
    }

    @Transactional
    public UserResponseDto edit(UserRequestEditDto userRequestEditDto, Long idUser){
        UserModel user = userRepository.findByIdUser(idUser);

        Utils.copyNonNullProperties(userRequestEditDto, user);

        UserModel userSaved = userRepository.save(user);

        return new UserResponseDto(
                userSaved.getIdUser(),
                userSaved.getDsUsername(),
                userSaved.getDsEmail(),
                userSaved.getDsPassword()
        );
    }
}
