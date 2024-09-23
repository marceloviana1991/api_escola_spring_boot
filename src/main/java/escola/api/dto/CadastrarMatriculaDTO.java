package escola.api.dto;

import jakarta.validation.constraints.NotNull;

public record CadastrarMatriculaDTO(
        @NotNull
        Long alunoId,
        @NotNull
        Long cursoId) {
}
