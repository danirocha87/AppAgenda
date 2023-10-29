package br.com.daniela.AppAgenda.model;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pessoa")
public class Pessoa {

  @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = true)
    private String endereco;
    
    @Column(nullable = true)
    private String cep;

     @Column(nullable = true)
    public String cidade;    

    @Column(nullable = true)
    private String uf;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pessoa", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Contato> contatos;

    public Pessoa() {}

    public Pessoa(Long id, String nome, String endereco, String cep, String uf) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.cep = cep;
        this.uf = uf;
    }  

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public String getNome() {
      return nome;
    }

    public void setNome(String nome) {
      this.nome = nome;
    }

    public String getEndereco() {
      return endereco;
    }

    public void setEndereco(String endereco) {
      this.endereco = endereco;
    }

    public String getCep() {
      return cep;
    }

    public void setCep(String cep) {
      this.cep = cep;
    }

    public String getCidade() {
      return cidade;
    }

    public void setCidade(String cidade) {
      this.cidade = cidade;
    }

    public String getUf() {
      return uf;
    }

    public void setUf(String uf) {
      this.uf = uf;
    }

    public List<Contato> getContatos() {
      return contatos;
    }

    public void setContatos(List<Contato> contatos) {
      this.contatos = contatos;
    }


  
}
