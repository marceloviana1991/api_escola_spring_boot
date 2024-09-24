package escola.api.controller;

import escola.api.dto.AtualizacaoCursoDTO;
import escola.api.dto.CadastroCursoDTO;
import escola.api.dto.ListagemCursoDTO;
import escola.api.model.Curso;
import escola.api.repository.CursoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    public void cadastrarCurso(@RequestBody @Valid CadastroCursoDTO cadastroCursoDTO) {
        cursoRepository.save(new Curso(cadastroCursoDTO));
    }

    @GetMapping
    public Page<ListagemCursoDTO> listarCursos(@PageableDefault(size = 30) Pageable pageable) {
        return cursoRepository.findAll(pageable).map(curso -> new ListagemCursoDTO(
                curso.getId(), curso.getNome(), curso.getTurno(), curso.getDataInicio(), curso.getDataTermino()));
    }

    @PutMapping
    @Transactional
    public void atualizarCuso(@RequestBody @Valid AtualizacaoCursoDTO atualizacaoCursoDTO) {
        Optional<Curso> cursoOptional = cursoRepository.findById(atualizacaoCursoDTO.id());
        cursoOptional.ifPresent(curso -> curso.atualizarInformacoes(atualizacaoCursoDTO));
    }

}
