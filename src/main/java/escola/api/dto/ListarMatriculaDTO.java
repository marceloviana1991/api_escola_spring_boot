package escola.api.dto;

import escola.api.model.Turno;

public record ListarMatriculaDTO(
        Long id,
        String nomeAluno,
        String nomeCurso,
        Turno turnoCurso
) {
}
