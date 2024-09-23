package escola.api.model;

import escola.api.dto.CadastrarAlunoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "alunos")
@Entity(name = "Aluno")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Aluno {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    public Aluno(CadastrarAlunoDTO cadastrarAlunoDTO) {
        this.nome = cadastrarAlunoDTO.nome();
    }

}
