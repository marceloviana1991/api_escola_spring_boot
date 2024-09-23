package escola.api.controller;

import escola.api.dto.CadastrarAlunoDTO;
import escola.api.model.Aluno;
import escola.api.repository.AlunoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @PostMapping
    @Transactional
    public void cadastrarAluno(@RequestBody CadastrarAlunoDTO cadastrarAlunoDTO) {
        alunoRepository.save(new Aluno(cadastrarAlunoDTO));
    }
}
