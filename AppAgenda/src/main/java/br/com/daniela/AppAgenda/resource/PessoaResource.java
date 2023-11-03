package br.com.daniela.AppAgenda.resource;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.daniela.AppAgenda.dto.ContatoRequestDto;
import br.com.daniela.AppAgenda.dto.PessoaDirectMailResponseDto;
import br.com.daniela.AppAgenda.dto.PessoaRequestDto;
import br.com.daniela.AppAgenda.dto.PessoaRequestUpdateDto;
import br.com.daniela.AppAgenda.model.Contato;
import br.com.daniela.AppAgenda.model.Pessoa;
import br.com.daniela.AppAgenda.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaResource {
    
    @Autowired
    private PessoaService pessoaService;
    
    public PessoaResource(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }   

    @Operation(summary = "Listar todas as pessoas")
    @GetMapping
    public ResponseEntity<List<Pessoa>> getAll(){
        List<Pessoa> pessoas = pessoaService.getAll();
        if(pessoas == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pessoas);
    }

    @Operation(summary = "Buscar pessoa por id")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pessoa>> getById(@PathVariable Long id){
        Optional<Pessoa> pessoa = pessoaService.getById(id);
        if(pessoa == null){
            return ResponseEntity.notFound().build(); 
        }
        return ResponseEntity.ok(pessoa);
    }

    @Operation(summary = "Buscar pessoa para montar mala direta a partir de um id")
    @GetMapping("/direct-mail/{id}")
    public ResponseEntity<PessoaDirectMailResponseDto> getByIdDirectMail(@PathVariable Long id){
        PessoaDirectMailResponseDto pessoa = pessoaService.getByIdDirectMail(id);
        if(pessoa == null){
            return ResponseEntity.notFound().build(); 
        }
        return ResponseEntity.ok(pessoa);
    }

    @Operation(summary = "Criar uma pessoa")
    @PostMapping
    public ResponseEntity<Pessoa> save(@RequestBody PessoaRequestDto pessoa){
        return new ResponseEntity<>(pessoaService.save(pessoa), HttpStatus.CREATED);
    }

    @Operation(summary = "Atualizar uma pessoa")
    @PutMapping
    public ResponseEntity<Pessoa> update(@RequestBody PessoaRequestUpdateDto pessoa){
        return new ResponseEntity<>(pessoaService.update(pessoa), HttpStatus.CREATED);
    }

    @Operation(summary = "Deletar uma pessoa")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        pessoaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Criar um contato a partir de uma pessoa")
    @PostMapping("/{id}/contatos")
    public ResponseEntity<Pessoa> save(@PathVariable Long id, @RequestBody ContatoRequestDto contato){
        return new ResponseEntity<>(pessoaService.saveContato(contato, id), HttpStatus.CREATED);
    }

    @Operation(summary = "Listar todos os contatos a partir de um id de uma pessoas")
     @GetMapping("/{id}/contatos")
    public ResponseEntity<List<Contato>> getAllByIdPerson(@PathVariable Long id){
        List<Contato> contatos =  pessoaService.getAllById(id);
        if(contatos == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(contatos);
    }
}
