package escola.api.controller;

import escola.api.dto.AtualizacaoAlunoDTO;
import escola.api.dto.CadastroAlunoDTO;
import escola.api.dto.ListagemAlunoDTO;
import escola.api.model.Aluno;
import escola.api.repository.AlunoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @PostMapping
    @Transactional
    public void cadastrarAluno(@RequestBody @Valid CadastroAlunoDTO cadastroAlunoDTO) {
        alunoRepository.save(new Aluno(cadastroAlunoDTO));
    }

    @GetMapping
    public Page<ListagemAlunoDTO> listarAlunos(@PageableDefault(size = 30) Pageable pageable) {
        return alunoRepository.findAll(pageable).map(aluno -> new ListagemAlunoDTO(aluno.getId(), aluno.getNome()));
    }

    @PutMapping
    @Transactional
    public void atualizarAluno(@RequestBody @Valid AtualizacaoAlunoDTO atualizacaoAlunoDTO) {
        Optional<Aluno> alunoOptional = alunoRepository.findById(atualizacaoAlunoDTO.id());
        alunoOptional.ifPresent(aluno -> aluno.atualizarInformacoes(atualizacaoAlunoDTO));

    }
}
