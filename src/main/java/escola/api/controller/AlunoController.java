package escola.api.controller;

import escola.api.dto.CadastrarAlunoDTO;
import escola.api.dto.ListarAlunoDTO;
import escola.api.model.Aluno;
import escola.api.repository.AlunoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @PostMapping
    @Transactional
    public void cadastrarAluno(@RequestBody @Valid CadastrarAlunoDTO cadastrarAlunoDTO) {
        alunoRepository.save(new Aluno(cadastrarAlunoDTO));
    }

    @GetMapping
    public List<ListarAlunoDTO> listarAlunos() {
        return alunoRepository.findAll().stream().map(aluno -> new ListarAlunoDTO(aluno.getId(), aluno.getNome())).toList();
    }
}
