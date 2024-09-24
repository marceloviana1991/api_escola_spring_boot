package escola.api.dto;

import jakarta.validation.constraints.NotNull;

public record CadastroMatriculaDTO(
        @NotNull
        Long alunoId,
        @NotNull
        Long cursoId
) {
}
