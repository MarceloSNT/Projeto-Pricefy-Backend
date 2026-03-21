package marcelo.project.pricefy.dto.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UserRequestEditDto(
        @Schema(description = "Inserir um username", example = "user.01", requiredMode = Schema.RequiredMode.REQUIRED)
        String dsUsername,

        @Schema(description = "Inserir um email do usuário", example = "user1@gmail.com", requiredMode = Schema.RequiredMode.REQUIRED)
        @Email
        String dsEmail,

        @Schema(description = "Inserir senha do usuário", example = "usuário.01", requiredMode = Schema.RequiredMode.REQUIRED)
        @Pattern(regexp ="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*.?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Pelo menos 1 letra minúscula (a-z)\n" +
                "✅ Pelo menos 1 letra maiúscula (A-Z)\n" +
                "✅ Pelo menos 1 número (0-9)\n" +
                "✅ Pelo menos 1 caractere especial (@$!%*?&)\n" +
                "✅ Tamanho mínimo: 8 caracteres")
        String dsPassword
){}
