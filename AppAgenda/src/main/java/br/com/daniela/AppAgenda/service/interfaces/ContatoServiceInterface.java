package br.com.daniela.AppAgenda.service.interfaces;


import java.util.Optional;

import br.com.daniela.AppAgenda.model.Contato;


public interface ContatoServiceInterface {
  Optional<Contato> getById(Long id);
  Contato update(Contato contato);
  void delete(Long id);
  
}
