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
        return ResponseEntity.created(uri).body(new ResponseAlunoDTO(aluno.getId(), aluno.getNome()));
    }

    @GetMapping
    public ResponseEntity<Page<ListagemAlunoDTO>> listarAlunos(@PageableDefault(size = 30) Pageable pageable) {
        Page<ListagemAlunoDTO> listagemAlunoDTOPage = alunoRepository.findAll(pageable).map(aluno -> new ListagemAlunoDTO(aluno.getId(), aluno.getNome()));
        return ResponseEntity.ok(listagemAlunoDTOPage);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ResponseAlunoDTO> atualizarAluno(@RequestBody @Valid AtualizacaoAlunoDTO atualizacaoAlunoDTO) {
        Optional<Aluno> alunoOptional = alunoRepository.findById(atualizacaoAlunoDTO.id());
        alunoOptional.ifPresent(aluno -> aluno.atualizarInformacoes(atualizacaoAlunoDTO));
        return ResponseEntity.ok(new ResponseAlunoDTO(alunoOptional.get().getId(), alunoOptional.get().getNome()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> excluirAluno(@PathVariable Long id) {
        alunoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListagemAlunoDTO> detalharAluno(@PathVariable Long id) {
        Optional<Aluno> alunoOptional = alunoRepository.findById(id);
        return alunoOptional.map(aluno -> ResponseEntity.ok(new ListagemAlunoDTO(aluno.getId(), aluno.getNome()))).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
