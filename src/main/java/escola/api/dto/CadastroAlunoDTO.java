package escola.api.dto;

import jakarta.validation.constraints.NotBlank;

public record CadastroAlunoDTO(
        @NotBlank
        String nome
) {
}
