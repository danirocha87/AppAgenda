package br.com.daniela.AppAgenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.daniela.AppAgenda.model.Pessoa;


@Repository
public interface PessoaRepository  extends JpaRepository<Pessoa, Long>{

  
}
