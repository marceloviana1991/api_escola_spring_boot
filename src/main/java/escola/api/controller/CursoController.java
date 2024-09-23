package escola.api.controller;

import escola.api.dto.CadastrarCursoDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @PostMapping
    public void cadastrarCurso(@RequestBody CadastrarCursoDTO cadastrarCursoDTO) {
        System.out.println(cadastrarCursoDTO);
    }
}
