package escola.api.dto;

import escola.api.model.Turno;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CadastroCursoDTO(
        @NotBlank
        String nome,
        @NotNull
        Turno turno,
        @NotBlank
        @Pattern(regexp = "[0-3][0-9]/[0-1][0-9]/[2-9][0-9][0-9][0-9]")
        String dataInicio,
        @NotBlank
        @Pattern(regexp = "[0-3][0-9]/[0-1][0-9]/[2-9][0-9][0-9][0-9]")
        String dataTermino
) {
}
