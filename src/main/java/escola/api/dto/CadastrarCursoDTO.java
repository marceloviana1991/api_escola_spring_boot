package escola.api.dto;

import escola.api.model.Turno;

public record CadastrarCursoDTO(String nome, Turno turno, String dataInicio, String dataTermino) {
}
