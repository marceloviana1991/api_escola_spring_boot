package escola.api.controller;

import escola.api.dto.CadastroMatriculaDTO;
import escola.api.dto.ListagemMatriculaDTO;
import escola.api.model.Aluno;
import escola.api.model.Curso;
import escola.api.model.Matricula;
import escola.api.repository.AlunoRepository;
import escola.api.repository.CursoRepository;
import escola.api.repository.MatriculaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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
    public ResponseEntity<CadastroMatriculaDTO> cadastrarMatricula(@RequestBody @Valid CadastroMatriculaDTO cadastroMatriculaDTO,
                                                                   UriComponentsBuilder uriComponentsBuilder) {
        Optional<Curso> curso = cursoRepository.findById(cadastroMatriculaDTO.cursoId());
        Optional<Aluno> aluno = alunoRepository.findById(cadastroMatriculaDTO.alunoId());

        if (aluno.isPresent() && curso.isPresent()) {
            Matricula matricula = new Matricula(aluno.get(), curso.get());
            matriculaRepository.save(matricula);
            var uri = uriComponentsBuilder.path("/matriculas/{id}").buildAndExpand(matricula.getId()).toUri();
            return ResponseEntity.created(uri).body(cadastroMatriculaDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<Page<ListagemMatriculaDTO>> listarMatriculas(@PageableDefault(size = 30) Pageable pageable,
                                                       @RequestParam(value = "cursoId", required = false) Long cursoId) {
        if (cursoId != null) {
            Page<ListagemMatriculaDTO> listagemMatriculaDTOPage = matriculaRepository.findAllBycursoId(cursoId, pageable).map(
                    matricula -> new ListagemMatriculaDTO(matricula.getId(), matricula.getAluno().getNome(),
                            matricula.getCurso().getNome(), matricula.getCurso().getTurno()));
            return ResponseEntity.ok(listagemMatriculaDTOPage);
        }
        Page<ListagemMatriculaDTO> listagemMatriculaDTOPage = matriculaRepository.findAll(pageable).map(
                matricula -> new ListagemMatriculaDTO(matricula.getId(), matricula.getAluno().getNome(),
                        matricula.getCurso().getNome(), matricula.getCurso().getTurno()));
        return ResponseEntity.ok(listagemMatriculaDTOPage);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> excluirMatricula(@PathVariable Long id) {
        matriculaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
