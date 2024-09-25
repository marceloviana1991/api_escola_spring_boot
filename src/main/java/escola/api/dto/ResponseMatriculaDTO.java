package escola.api.dto;

import escola.api.model.Matricula;

public record ResponseMatriculaDTO(
        Long id,
        Long alunoId,
        Long cursoId
) {

    public ResponseMatriculaDTO(Matricula matricula) {
        this(matricula.getId(),
                matricula.getAluno().getId(),
                matricula.getCurso().getId());
    }
}
