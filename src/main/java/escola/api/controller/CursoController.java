package escola.api.controller;

import escola.api.dto.CadastrarCursoDTO;
import escola.api.dto.ListarCursoDTO;
import escola.api.model.Curso;
import escola.api.repository.CursoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    public void cadastrarCurso(@RequestBody @Valid CadastrarCursoDTO cadastrarCursoDTO) {
        cursoRepository.save(new Curso(cadastrarCursoDTO));
    }

    @GetMapping
    public Page<ListarCursoDTO> listarCursos(@PageableDefault(size = 30) Pageable pageable) {
        return cursoRepository.findAll(pageable).map(curso -> new ListarCursoDTO(
                curso.getId(), curso.getNome(), curso.getTurno(), curso.getDataInicio(), curso.getDataTermino()));
    }
}
