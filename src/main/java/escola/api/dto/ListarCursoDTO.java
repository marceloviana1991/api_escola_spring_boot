package escola.api.dto;

import escola.api.model.Turno;

import java.time.LocalDate;

public record ListarCursoDTO(
        Long id,
        String nome,
        Turno turno,
        LocalDate dataInicio,
        LocalDate dataTermino
) {
}
