package br.com.daniela.AppAgenda.resource;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.daniela.AppAgenda.model.Contato;
import br.com.daniela.AppAgenda.service.ContatoService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/contatos")
public class ContatoResource {

    @Autowired
    ContatoService contatoService;

    @Operation(summary = "Buscar contato por id")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Contato>> getById(@PathVariable Long id){
        Optional<Contato> contato =  contatoService.getById(id);
        if(contato == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(contato);
    }

    @Operation(summary = "Atualizar contato a partir de um id")
     @PutMapping
    public ResponseEntity<Contato> update(@RequestBody Contato pessoa){
        return new ResponseEntity<>(contatoService.update(pessoa), HttpStatus.CREATED);
    }

    @Operation(summary = "Deletar um contato a partir de um id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        contatoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
