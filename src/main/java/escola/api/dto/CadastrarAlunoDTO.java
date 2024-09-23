package escola.api.dto;

import jakarta.validation.constraints.NotBlank;

public record CadastrarAlunoDTO(
        @NotBlank
        String nome
) {
}
