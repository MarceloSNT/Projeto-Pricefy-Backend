package marcelo.project.pricefy.dto.response.user;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO de response user")
public record UserResponseDto(
        Long idUser,
        String dsUsername,
        String dsEmail,
        String dsPassword
) {}
