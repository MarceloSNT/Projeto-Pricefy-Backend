package marcelo.project.pricefy.dto.response;

public record LoginResponseDto(
        String token,
        Long idUser,
        String dsUsername,
        String dsEmail
        ) {
}
