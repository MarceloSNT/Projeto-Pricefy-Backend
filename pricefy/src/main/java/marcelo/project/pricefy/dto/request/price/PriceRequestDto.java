package marcelo.project.pricefy.dto.request.price;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "DTO request de price")
public record PriceRequestDto(
        Long idPrice,

        @NotNull(message = "Valor é obrigatório")
        @Schema(description = "Inserir valor do produto", example = "10.00", requiredMode = Schema.RequiredMode.REQUIRED)
        Double vlProduto,

        @NotNull(message = "Id do produto é orbigatório")
        @Schema(description = "Inserir id do produto", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
        Long product,

        @NotNull(message = "Id do mercado é orbigatório")
        @Schema(description = "Inserir id do mercado", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
        Long market
        ){}
