package br.com.daniela.AppAgenda.dto;

public record PessoaRequestUpdateDto(Long id, String nome, String endereco, String cep, String cidade, String uf) {
    
}

