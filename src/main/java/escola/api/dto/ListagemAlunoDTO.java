package escola.api.dto;

import escola.api.model.Aluno;

public record ListagemAlunoDTO(
        Long id,
        String nome
) {

    public ListagemAlunoDTO(Aluno aluno) {
        this(aluno.getId(), aluno.getNome());
    }
}
