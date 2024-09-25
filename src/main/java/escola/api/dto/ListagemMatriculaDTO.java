package escola.api.dto;

import escola.api.model.Matricula;
import escola.api.model.Turno;

public record ListagemMatriculaDTO(
        Long id,
        Long alunoId,
        String nomeAluno,
        Long cursoId,
        String nomeCurso,
        Turno turnoCurso
) {

    public ListagemMatriculaDTO(Matricula matricula) {
        this(matricula.getId(),
                matricula.getAluno().getId(),
                matricula.getAluno().getNome(),
                matricula.getCurso().getId(),
                matricula.getCurso().getNome(),
                matricula.getCurso().getTurno());
    }
}
