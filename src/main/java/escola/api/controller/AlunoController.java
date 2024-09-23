package escola.api.controller;

import escola.api.dto.CadastrarAlunoDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @PostMapping
    public void cadastrarAluno(@RequestBody CadastrarAlunoDTO cadastrarAlunoDTO) {
        System.out.println(cadastrarAlunoDTO);
    }
}
