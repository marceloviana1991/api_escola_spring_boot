package escola.api.dto;

import escola.api.model.Turno;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record AtualizacaoCursoDTO(
        @NotNull
        Long id,
        String nome,
        Turno turno,
        @Pattern(regexp = "[0-3][0-9]/[0-1][0-9]/[2-9][0-9][0-9][0-9]")
        String dataInicio,
        @Pattern(regexp = "[0-3][0-9]/[0-1][0-9]/[2-9][0-9][0-9][0-9]")
        String dataTermino
) {
}
