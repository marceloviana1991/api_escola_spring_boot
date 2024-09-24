package escola.api.dto;

import escola.api.model.Turno;

public record ListagemMatriculaDTO(
        Long id,
        String nomeAluno,
        String nomeCurso,
        Turno turnoCurso
) {
}
