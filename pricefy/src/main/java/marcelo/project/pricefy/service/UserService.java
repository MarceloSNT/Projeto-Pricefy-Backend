package marcelo.project.pricefy.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import marcelo.project.pricefy.dto.request.UserRequestDto;
import marcelo.project.pricefy.dto.response.UserResponseDto;
import marcelo.project.pricefy.entity.UserModel;
import marcelo.project.pricefy.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<UserResponseDto> listById(Long id) {
        return userRepository.findByIdUser(id);
    }
}
