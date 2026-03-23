package marcelo.project.pricefy.dto.request.market;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "DTO request de market")
public record MarketRequestDto(
        Long idMarket,

        @NotNull(message = "Nome do mercado é obrigatório")
        @Schema(description = "Inserir o nome do mercado", example = "Koch", requiredMode = Schema.RequiredMode.REQUIRED)
        String dsName
){}
