package com.fatec.livrariaecommerce.models.dto;

import com.fatec.livrariaecommerce.models.domain.Cliente;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
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

    public ClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.sobrenome = cliente.getSobrenome();
        this.dataNascimento = cliente.getDataNascimento();
        this.cpf = cliente.getDocumentos().stream().findFirst().get().getCodigo();
        this.email = cliente.getUsuario().getEmail();
        this.enderecos = cliente.getEnderecos().stream().map(EnderecoDTO::from).collect(Collectors.toList());
    }
}
