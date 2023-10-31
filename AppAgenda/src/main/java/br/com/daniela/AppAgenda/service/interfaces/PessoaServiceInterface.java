package br.com.daniela.AppAgenda.service.interfaces;


import java.util.List;
import java.util.Optional;

import br.com.daniela.AppAgenda.dto.ContatoRequestDto;
import br.com.daniela.AppAgenda.dto.PessoaDirectMailResponseDto;
import br.com.daniela.AppAgenda.dto.PessoaRequestDto;
import br.com.daniela.AppAgenda.dto.PessoaRequestUpdateDto;
import br.com.daniela.AppAgenda.model.Contato;
import br.com.daniela.AppAgenda.model.Pessoa;





public interface PessoaServiceInterface {
    Pessoa save(PessoaRequestDto pessoa);
    Pessoa saveContato(ContatoRequestDto contatoDto, Long id);
    Optional<Pessoa> getById(Long id);
    List<Contato> getAllById(Long id);
    PessoaDirectMailResponseDto getByIdDirectMail(Long id);
    List<Pessoa> getAll();
    Pessoa update(PessoaRequestUpdateDto pessoa);
    void delete(Long id);
}
