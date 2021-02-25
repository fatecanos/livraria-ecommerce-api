package com.fatec.livrariaecommerce.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ClienteDTO {
    private int id;
    private String nome;
    private String sobrenome;
    private LocalDate dataNascimento;
    private String cpf;
    private String email;
    private String senha;
    private String confirmacaoSenha;
}
