package marcelo.project.pricefy.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import marcelo.project.pricefy.dto.request.UserRequestDto;
import marcelo.project.pricefy.dto.response.UserResponseDto;
import marcelo.project.pricefy.entity.UserModel;
import marcelo.project.pricefy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
@Tag(name = "Usuário", description = "Endpoints para usuário")
@RequestMapping("pricefy/user/")
public class UserController {

    @Autowired
    private final UserService userService;

    @PostMapping("register")
    @Operation(summary = "Cadastro de usuário",description = "Realiza o cadastro do usuário")
    public ResponseEntity<UserResponseDto> register(@Valid @RequestBody UserRequestDto userRequest){
        UserResponseDto user = userService.createUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

}
