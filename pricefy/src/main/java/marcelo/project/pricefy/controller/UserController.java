package marcelo.project.pricefy.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import marcelo.project.pricefy.dto.request.user.LoginRequestDto;
import marcelo.project.pricefy.dto.request.user.UserRequestDto;
import marcelo.project.pricefy.dto.request.user.UserRequestEditDto;
import marcelo.project.pricefy.dto.response.LoginResponseDto;
import marcelo.project.pricefy.dto.response.UserResponseDto;
import marcelo.project.pricefy.entity.UserModel;
import marcelo.project.pricefy.repository.UserRepository;
import marcelo.project.pricefy.security.TokenConfig;
import marcelo.project.pricefy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
@Tag(name = "Usuário", description = "Endpoints para usuário")
@RequestMapping("pricefy/user/")
public class UserController {

    @Autowired
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenConfig tokenConfig;
    private final UserRepository userRepository;

    @PostMapping("register")
    @Operation(summary = "Cadastro de usuário",description = "Realiza o cadastro do usuário")
    public ResponseEntity<UserResponseDto> register(@Valid @RequestBody UserRequestDto userRequest){
        UserResponseDto user = userService.createUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("login")
    @Operation(summary = "Login de usuário", description = "Realiza a autenticação no sistema")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto){
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequestDto.dsUsername(),loginRequestDto.dsPassowrd());
        authenticationManager.authenticate(authToken);

        UserModel user = userRepository.findByDsUsername(loginRequestDto.dsUsername());
        String token = tokenConfig.generateToken(user);

        return ResponseEntity.ok(new LoginResponseDto(token, user.getIdUser(), user.getDsUsername(), user.getDsEmail()));
    }

    @GetMapping("listUser/{idUser}")
    @PreAuthorize("#idUser == authentication.principal.idUser")
    @Operation(summary = "Buscar usuário pelo id", description = "Busca usuário pole id informado")
    public UserModel listByIdUser(@PathVariable Long idUser){
        return userService.listById(idUser);
    }

    @PutMapping("edit/{idUser}")
    @PreAuthorize("#idUser == authentication.principal.idUser")
    @Operation(summary = "Editar usuário", description = "Editar dados parciais do usuário")
    public ResponseEntity<UserResponseDto> editUser(@PathVariable Long idUser, @Valid @RequestBody UserRequestEditDto userRequestEditDto){
        return ResponseEntity.status(HttpStatus.OK).body(userService.edit(userRequestEditDto, idUser));
    }

    @DeleteMapping("delete/{idUser}")
    @PreAuthorize("#idUser == authentication.principal.idUser")
    @Operation(summary = "Deletar usuário",description = "Deletar usuário pelo idUser")
    public void deleteUser(Long idUser){
        userService.deleteById(idUser);
    }
}
