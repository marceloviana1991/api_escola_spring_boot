package escola.api.dto;

import escola.api.model.Aluno;

public record ResponseAlunoDTO(
        Long id,
        String nome
) {
    public ResponseAlunoDTO(Aluno aluno) {
        this(aluno.getId(), aluno.getNome());
    }
}
