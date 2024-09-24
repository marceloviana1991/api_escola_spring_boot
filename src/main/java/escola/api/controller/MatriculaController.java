package escola.api.controller;

import escola.api.dto.CadastrarMatriculaDTO;
import escola.api.dto.ListarMatriculaDTO;
import escola.api.model.Aluno;
import escola.api.model.Curso;
import escola.api.model.Matricula;
import escola.api.repository.AlunoRepository;
import escola.api.repository.CursoRepository;
import escola.api.repository.MatriculaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaRepository matriculaRepository;
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    public void cadastrarMatricula(@RequestBody @Valid CadastrarMatriculaDTO cadastrarMatriculaDTO) {
        Optional<Curso> curso = cursoRepository.findById(cadastrarMatriculaDTO.cursoId());
        Optional<Aluno> aluno = alunoRepository.findById(cadastrarMatriculaDTO.alunoId());
        if (aluno.isPresent() && curso.isPresent()) {
            matriculaRepository.save(new Matricula(aluno.get(), curso.get()));
        }
    }

    @GetMapping
    public List<ListarMatriculaDTO> listarMatriculas() {
        return matriculaRepository.findAll().stream().map(
                matricula -> new ListarMatriculaDTO(matricula.getId(), matricula.getAluno().getNome(),
                        matricula.getCurso().getNome(), matricula.getCurso().getTurno())).toList();
    }
}
