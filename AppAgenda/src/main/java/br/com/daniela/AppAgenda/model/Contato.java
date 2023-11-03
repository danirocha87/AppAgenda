package br.com.daniela.AppAgenda.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "contato")
public class Contato {

  @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Integer tipoContato;
    
    @Column(nullable = false)
    private String contato;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id")
    @JsonIgnore
    private Pessoa pessoa;
    
    public Contato() {}

    public Contato(Long id, Integer tipoContato, String contato) {
        this.id = id;
        this.tipoContato = tipoContato;
        this.contato = contato;        
    }

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public Integer getTipoContato() {
      return tipoContato;
    }

    public void setTipoContato(Integer tipoContato) {
      this.tipoContato = tipoContato;
    }

    public String getContato() {
      return contato;
    }

    public void setContato(String contato) {
      this.contato = contato;
    }

    public Pessoa getPessoa() {
      return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
      this.pessoa = pessoa;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      result = prime * result + ((tipoContato == null) ? 0 : tipoContato.hashCode());
      result = prime * result + ((contato == null) ? 0 : contato.hashCode());
      result = prime * result + ((pessoa == null) ? 0 : pessoa.hashCode());
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
      Contato other = (Contato) obj;
      if (id == null) {
        if (other.id != null)
          return false;
      } else if (!id.equals(other.id))
        return false;
      if (tipoContato == null) {
        if (other.tipoContato != null)
          return false;
      } else if (!tipoContato.equals(other.tipoContato))
        return false;
      if (contato == null) {
        if (other.contato != null)
          return false;
      } else if (!contato.equals(other.contato))
        return false;
      if (pessoa == null) {
        if (other.pessoa != null)
          return false;
      } else if (!pessoa.equals(other.pessoa))
        return false;
      return true;
    }

    

  
}
