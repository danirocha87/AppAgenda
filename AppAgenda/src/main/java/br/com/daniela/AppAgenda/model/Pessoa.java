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

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      result = prime * result + ((nome == null) ? 0 : nome.hashCode());
      result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
      result = prime * result + ((cep == null) ? 0 : cep.hashCode());
      result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
      result = prime * result + ((uf == null) ? 0 : uf.hashCode());
      result = prime * result + ((contatos == null) ? 0 : contatos.hashCode());
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      Pessoa other = (Pessoa) obj;
      if (id == null) {
        if (other.id != null)
          return false;
      } else if (!id.equals(other.id))
        return false;
      if (nome == null) {
        if (other.nome != null)
          return false;
      } else if (!nome.equals(other.nome))
        return false;
      if (endereco == null) {
        if (other.endereco != null)
          return false;
      } else if (!endereco.equals(other.endereco))
        return false;
      if (cep == null) {
        if (other.cep != null)
          return false;
      } else if (!cep.equals(other.cep))
        return false;
      if (cidade == null) {
        if (other.cidade != null)
          return false;
      } else if (!cidade.equals(other.cidade))
        return false;
      if (uf == null) {
        if (other.uf != null)
          return false;
      } else if (!uf.equals(other.uf))
        return false;
      if (contatos == null) {
        if (other.contatos != null)
          return false;
      } else if (!contatos.equals(other.contatos))
        return false;
      return true;
    }


  
}
