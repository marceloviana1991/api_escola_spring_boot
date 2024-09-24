package escola.api.dto;

import escola.api.model.Turno;

import java.time.LocalDate;

public record ListagemCursoDTO(
        Long id,
        String nome,
        Turno turno,
        LocalDate dataInicio,
        LocalDate dataTermino
) {
}
