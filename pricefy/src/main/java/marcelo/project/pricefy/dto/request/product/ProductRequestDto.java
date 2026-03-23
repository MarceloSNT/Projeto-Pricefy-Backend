package marcelo.project.pricefy.dto.request.product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record ProductRequestDto(
    Long idProduct,

    @NotNull(message = "Nome do produto é obrigatório")
    @Schema(description = "Inserir o nome do produto", example = "Arroz parborizado", requiredMode = Schema.RequiredMode.REQUIRED)
    String dsName
    ) {}
