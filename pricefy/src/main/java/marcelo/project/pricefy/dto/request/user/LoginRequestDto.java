package marcelo.project.pricefy.dto.request.user;

import io.swagger.v3.oas.annotations.media.Schema;

public record LoginRequestDto(
        @Schema(example = "User")
        String dsUsername,
        @Schema(example = "User@19")
        String dsPassword
) {
}
