package escola.api.model;

import escola.api.dto.AtualizacaoCursoDTO;
import escola.api.dto.CadastroCursoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Table(name = "cursos")
@Entity(name = "Curso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private Turno turno;
    private LocalDate dataInicio;
    private LocalDate dataTermino;
    static private final DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Curso(CadastroCursoDTO cadastroCursoDTO) {
        this.nome = cadastroCursoDTO.nome();
        this.turno = cadastroCursoDTO.turno();
        this.setDataInicio(cadastroCursoDTO.dataInicio());
        this.setDataTermino(cadastroCursoDTO.dataTermino());
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = LocalDate.from(formatador.parse(dataInicio));
    }

    public void setDataTermino(String dataTermino) {
        this.dataTermino = LocalDate.from(formatador.parse(dataTermino));
    }

    public void atualizarInformacoes(AtualizacaoCursoDTO atualizacaoCursoDTO) {
        if (atualizacaoCursoDTO.nome() != null) {
            this.nome = atualizacaoCursoDTO.nome();
        }
        if (atualizacaoCursoDTO.turno() != null) {
            this.turno = atualizacaoCursoDTO.turno();
        }
        if (atualizacaoCursoDTO.dataInicio() != null) {
            this.setDataInicio(atualizacaoCursoDTO.dataInicio());
        }
        if (atualizacaoCursoDTO.dataTermino() != null) {
            this.setDataTermino(atualizacaoCursoDTO.dataTermino());
        }
    }
}
