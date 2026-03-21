package marcelo.project.pricefy.dto.response.user;

public record LoginResponseDto(
        String token,
        Long idUser,
        String dsUsername,
        String dsEmail
        ) {
}
