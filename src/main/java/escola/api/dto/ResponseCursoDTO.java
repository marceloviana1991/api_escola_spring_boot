package escola.api.dto;

import escola.api.model.Curso;
import escola.api.model.Turno;

import java.time.LocalDate;

public record ResponseCursoDTO(
        Long id,
        String nome,
        Turno turno,
        LocalDate dataInicio,
        LocalDate dataTermino
) {

    public ResponseCursoDTO(Curso curso) {
        this(curso.getId(),
                curso.getNome(),
                curso.getTurno(),
                curso.getDataInicio(),
                curso.getDataTermino());
    }
}
