package marcelo.project.pricefy.dto.request.user;

import io.swagger.v3.oas.annotations.media.Schema;

public record LoginRequestDto(
        @Schema(example = "Marcelo")
        String dsUsername,
        @Schema(example = "Marcelo@19")
        String dsPassowrd
) {
}
