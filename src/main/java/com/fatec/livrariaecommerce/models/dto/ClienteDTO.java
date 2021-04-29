package com.fatec.livrariaecommerce.models.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data @NoArgsConstructor
public class ClienteDTO {
    private int id;
    private String nome;
    private String sobrenome;
    private LocalDate dataNascimento;
    private String cpf;
    private String email;
    private String senha;
    private String confirmacaoSenha;
    private List<EnderecoDTO> enderecos;

    @Builder(builderMethodName = "montar")
    public ClienteDTO(int id, String nome, String sobrenome,
                      LocalDate dataNascimento, String cpf,
                      String email, String senha, String confirmacaoSenha,
                      List<EnderecoDTO> enderecos) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.confirmacaoSenha = confirmacaoSenha;
        this.enderecos = enderecos;
    }
}
