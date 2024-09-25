package escola.api.controller;

import escola.api.dto.AtualizacaoCursoDTO;
import escola.api.dto.CadastroCursoDTO;
import escola.api.dto.ListagemCursoDTO;
import escola.api.dto.ResponseCursoDTO;
import escola.api.model.Curso;
import escola.api.repository.CursoRepository;
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
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseCursoDTO> cadastrarCurso(@RequestBody @Valid CadastroCursoDTO cadastroCursoDTO,
                                                           UriComponentsBuilder uriComponentsBuilder) {
        Curso curso = new Curso(cadastroCursoDTO);
        cursoRepository.save(curso);
        var uri = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(uri).body(new ResponseCursoDTO(
                curso.getId(), curso.getNome(), curso.getTurno(),
                curso.getDataInicio(), curso.getDataTermino()));
    }

    @GetMapping
    public ResponseEntity<Page<ListagemCursoDTO>> listarCursos(@PageableDefault(size = 30) Pageable pageable) {
        Page<ListagemCursoDTO> listagemCursoDTOPage = cursoRepository.findAll(pageable).map(curso -> new ListagemCursoDTO(
                curso.getId(), curso.getNome(), curso.getTurno(), curso.getDataInicio(), curso.getDataTermino()));
        return ResponseEntity.ok(listagemCursoDTOPage);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ResponseCursoDTO> atualizarCurso(@RequestBody @Valid AtualizacaoCursoDTO atualizacaoCursoDTO) {
        Optional<Curso> cursoOptional = cursoRepository.findById(atualizacaoCursoDTO.id());
        cursoOptional.ifPresent(curso -> curso.atualizarInformacoes(atualizacaoCursoDTO));
        return ResponseEntity.ok(new ResponseCursoDTO(
                cursoOptional.get().getId(), cursoOptional.get().getNome(), cursoOptional.get().getTurno(),
                cursoOptional.get().getDataInicio(), cursoOptional.get().getDataTermino()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> excluirCurso(@PathVariable Long id) {
        cursoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListagemCursoDTO> detalharCurso(@PathVariable Long id) {
        Optional<Curso> cursoOptional = cursoRepository.findById(id);
        return cursoOptional.map(curso -> ResponseEntity.ok(new ListagemCursoDTO(curso.getId(), curso.getNome(),
                curso.getTurno(), curso.getDataInicio(), curso.getDataTermino()))).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
