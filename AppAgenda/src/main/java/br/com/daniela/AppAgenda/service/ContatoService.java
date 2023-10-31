package br.com.daniela.AppAgenda.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.daniela.AppAgenda.model.Contato;
import br.com.daniela.AppAgenda.repository.ContatoRepository;
import br.com.daniela.AppAgenda.service.interfaces.ContatoServiceInterface;

@Service
public class ContatoService implements ContatoServiceInterface {

    @Autowired
    ContatoRepository repository;

    @Autowired 
    PessoaService pessoaService;
    
    @Override
    public Optional<Contato> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Contato update(Contato contato) {     
        validacaoContatoBasico(contato);
       Contato upContato = validacaoContatoExiste(contato.getId());

        Contato newContato = upContato;
        newContato.setContato(contato.getContato());
        newContato.setTipoContato(contato.getTipoContato());
        return repository.save(newContato);        
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }


    private void validacaoContatoBasico(Contato contato){
        if(contato == null){
            throw new ResourceNotFoundException("Objeto Contato vazio");
        }
       if(Strings.isEmpty(contato.getContato())){
        throw new ResourceNotFoundException("Contato é obrigatório");
       }

       if(contato.getTipoContato() != 0 && contato.getTipoContato() != 1){
        throw new ResourceNotFoundException("Tipo de Contato inválido");
       }      
    }      

    private Contato validacaoContatoExiste(Long id){
        Optional<Contato> contato = repository.findById(id);
        if(!contato.isPresent()){
            throw new ResourceNotFoundException("Contato não encontrado no banco de dados");
        }
        return contato.get();
    }
}


