package escola.api.controller;

import escola.api.dto.CadastrarCursoDTO;
import escola.api.model.Curso;
import escola.api.repository.CursoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    public void cadastrarCurso(@RequestBody CadastrarCursoDTO cadastrarCursoDTO) {
        cursoRepository.save(new Curso(cadastrarCursoDTO));
    }
}
