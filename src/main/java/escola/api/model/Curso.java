package escola.api.model;

import escola.api.dto.CadastrarCursoDTO;
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

    public Curso(CadastrarCursoDTO cadastrarCursoDTO) {
        this.nome = cadastrarCursoDTO.nome();
        this.turno = cadastrarCursoDTO.turno();
        this.setDataInicio(cadastrarCursoDTO.dataInicio());
        this.setDataTermino(cadastrarCursoDTO.dataTermino());
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = LocalDate.from(formatador.parse(dataInicio));
    }

    public void setDataTermino(String dataTermino) {
        this.dataTermino = LocalDate.from(formatador.parse(dataTermino));
    }

}
