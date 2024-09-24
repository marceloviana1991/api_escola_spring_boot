package escola.api.controller;

import escola.api.dto.CadastrarAlunoDTO;
import escola.api.dto.ListarAlunoDTO;
import escola.api.model.Aluno;
import escola.api.repository.AlunoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

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
    public Page<ListarAlunoDTO> listarAlunos(@PageableDefault(size = 30) Pageable pageable) {
        return alunoRepository.findAll(pageable).map(aluno -> new ListarAlunoDTO(aluno.getId(), aluno.getNome()));
    }
}
