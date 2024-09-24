package escola.api.dto;

import jakarta.validation.constraints.NotNull;

public record AtualizacaoAlunoDTO(
        @NotNull
        Long id,
        String nome
) {
}
