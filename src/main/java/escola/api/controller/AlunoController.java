package escola.api.controller;

import escola.api.dto.AtualizacaoAlunoDTO;
import escola.api.dto.CadastroAlunoDTO;
import escola.api.dto.ListagemAlunoDTO;
import escola.api.dto.ResponseAlunoDTO;
import escola.api.model.Aluno;
import escola.api.repository.AlunoRepository;
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
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseAlunoDTO> cadastrarAluno(@RequestBody @Valid CadastroAlunoDTO cadastroAlunoDTO,
                                                           UriComponentsBuilder uriComponentsBuilder) {
        Aluno aluno = new Aluno(cadastroAlunoDTO);
        alunoRepository.save(aluno);
        var uri = uriComponentsBuilder.path("/alunos/{id}").buildAndExpand(aluno.getId()).toUri();
        return ResponseEntity.created(uri).body(new ResponseAlunoDTO(aluno));
    }

    @GetMapping
    public ResponseEntity<Page<ListagemAlunoDTO>> listarAlunos(@PageableDefault(size = 30) Pageable pageable) {
        Page<ListagemAlunoDTO> listagemAlunoDTOPage = alunoRepository.findAll(pageable).map(ListagemAlunoDTO::new);
        return ResponseEntity.ok(listagemAlunoDTOPage);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ResponseAlunoDTO> atualizarAluno(@RequestBody @Valid AtualizacaoAlunoDTO atualizacaoAlunoDTO) {
        Aluno aluno = alunoRepository.getReferenceById(atualizacaoAlunoDTO.id());
        aluno.atualizarInformacoes(atualizacaoAlunoDTO);
        return ResponseEntity.ok(new ResponseAlunoDTO(aluno));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> excluirAluno(@PathVariable Long id) {
        Aluno aluno = alunoRepository.getReferenceById(id);
        alunoRepository.delete(aluno);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListagemAlunoDTO> detalharAluno(@PathVariable Long id) {
        Aluno aluno = alunoRepository.getReferenceById(id);
        return ResponseEntity.ok(new ListagemAlunoDTO(aluno));
    }
}
