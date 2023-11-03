package br.com.daniela.AppAgenda.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.daniela.AppAgenda.dto.ContatoRequestDto;
import br.com.daniela.AppAgenda.dto.PessoaDirectMailResponseDto;
import br.com.daniela.AppAgenda.dto.PessoaRequestDto;
import br.com.daniela.AppAgenda.dto.PessoaRequestUpdateDto;
import br.com.daniela.AppAgenda.exception.ResourceNotFoundException;
import br.com.daniela.AppAgenda.model.Contato;
import br.com.daniela.AppAgenda.model.Pessoa;
import br.com.daniela.AppAgenda.repository.PessoaRepository;
import br.com.daniela.AppAgenda.service.interfaces.PessoaServiceInterface;

@Service
public class PessoaService implements PessoaServiceInterface {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public Pessoa save(PessoaRequestDto pessoaRequest) {
        if(pessoaRequest == null){
            throw new ResourceNotFoundException("Objeto pessoa vazio");
        }
        if(Strings.isEmpty(pessoaRequest.nome())){
            throw new ResourceNotFoundException("Nome é obrigatório");
        }   
        
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(pessoaRequest.nome());
        pessoa.setCep(pessoaRequest.cep());
        pessoa.setCidade(pessoaRequest.cidade());
        pessoa.setEndereco(pessoaRequest.endereco());
        pessoa.setUf(pessoaRequest.uf());
        return pessoaRepository.save(pessoa);
    }

     @Override
    public Pessoa saveContato(ContatoRequestDto contatoDto, Long id) {       

        if(contatoDto == null){
            throw new ResourceNotFoundException("Objeto Contato vazio");
        }
       if(Strings.isEmpty(contatoDto.contato())){
        throw new ResourceNotFoundException("Contato é obrigatório");
       }

       if(contatoDto.tipoContato() != 0 && contatoDto.tipoContato() != 1){
        throw new ResourceNotFoundException("Tipo de Contato inválido");
       }  
       
        Contato contato = new Contato();
        contato.setContato(contatoDto.contato());
        contato.setTipoContato(contatoDto.tipoContato());

        Pessoa pessoa = validacaoPessoaExiste(id);

       List<Contato> contatos = new ArrayList<>();
       
       contato.setPessoa(pessoa);
       contatos.add(contato);

       pessoa.setContatos(contatos);
       return pessoaRepository.save(pessoa);
    }

    @Override
    public Optional<Pessoa> getById(Long id) {
        return pessoaRepository.findById(id);
    }

    @Override
    public PessoaDirectMailResponseDto getByIdDirectMail(Long id) {

        Pessoa pessoa = validacaoPessoaExiste(id);

        if(Strings.isEmpty(pessoa.getEndereco())){
            throw new ResourceNotFoundException("Não é possível montar mala direta sem endereço");
        }
        if(Strings.isEmpty(pessoa.getCep())){
            throw new ResourceNotFoundException("Não é possível montar mala direta sem cep");
        }
         if(Strings.isEmpty(pessoa.getCidade())){
            throw new ResourceNotFoundException("Não é possível montar mala direta sem a cidade");
        }
         if(Strings.isEmpty(pessoa.getUf())){
            throw new ResourceNotFoundException("Não é possível montar mala direta sem UF");
        }

        String malaDireta = ""
        .concat(pessoa.getEndereco())
        .concat(", - CEP: ")
        .concat(pessoa.getCep())
        .concat(" - ")
        .concat(pessoa.getCidade())
        .concat("/")
        .concat(pessoa.getUf());

        PessoaDirectMailResponseDto pessoalDto = new PessoaDirectMailResponseDto(pessoa.getId(), pessoa.getNome(), malaDireta);

        return pessoalDto;
    }

    @Override
    public List<Pessoa> getAll() {
        return pessoaRepository.findAll();
    }

     @Override
    public List<Contato> getAllById(Long id) {
        Optional<Pessoa> pessoaOpcional = pessoaRepository.findById(id);
        if(!pessoaOpcional.isPresent()){
            throw new ResourceNotFoundException("Não foi encontrado nenhuma pessoa a partir do ID informado");
        }
        Pessoa pessoa = pessoaOpcional.get();
        List<Contato> contatos = new ArrayList<>();

        contatos = pessoa.getContatos();
        return contatos;
    }

    @Override
    public Pessoa update(PessoaRequestUpdateDto pessoa) {
        if(pessoa == null){
            throw new ResourceNotFoundException("Objeto pessoa vazio");
        }
        if(Strings.isEmpty(pessoa.nome())){
            throw new ResourceNotFoundException("Nome é obrigatório");
        }

        Pessoa newPessoa = validacaoPessoaExiste(pessoa.id());

        newPessoa.setNome(pessoa.nome());
        newPessoa.setEndereco(pessoa.endereco());
        newPessoa.setCep(pessoa.cep());
        newPessoa.setCidade(pessoa.cidade());
        newPessoa.setUf(pessoa.uf());
        return pessoaRepository.save(newPessoa);       
    }

    @Override
    public void delete(Long id) {
        pessoaRepository.deleteById(id);
    }   
    
    private Pessoa validacaoPessoaExiste(Long id){
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);

        if(!pessoa.isPresent()){
            throw new ResourceNotFoundException("Não foi encontrado nenhuma pessoa com o Id informado");
        }
        return pessoa.get();
    }   
}

