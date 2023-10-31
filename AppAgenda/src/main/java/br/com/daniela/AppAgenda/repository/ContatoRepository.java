package br.com.daniela.AppAgenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.daniela.AppAgenda.model.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {    
  
}
