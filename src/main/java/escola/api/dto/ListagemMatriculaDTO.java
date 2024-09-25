package escola.api.dto;

import escola.api.model.Turno;

public record ListagemMatriculaDTO(
        Long id,
        Long alunoId,
        String nomeAluno,
        Long cursoId,
        String nomeCurso,
        Turno turnoCurso
) {
}
