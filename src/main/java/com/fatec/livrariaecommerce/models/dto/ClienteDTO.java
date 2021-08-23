package com.fatec.livrariaecommerce.models.dto;

import com.fatec.livrariaecommerce.models.domain.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
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
    private String genero;
    private String email;
    private String senha;
    private String confirmacaoSenha;
    private boolean ativo;
    private List<TelefoneDTO> telefones;
    private List<EnderecoDTO> enderecos;

    public void fill(Cliente dominio) {
        Usuario usuario;
        if (dominio.getUsuario() == null) {
            usuario = new Usuario(this.getEmail(), this.getSenha(), PerfilUsuario.CLIENTE);
        } else {
            usuario = dominio.getUsuario();
        }

        dominio.atualizarDados(this.id, this.nome, this.sobrenome, this.dataNascimento, this.cpf,
                this.genero, usuario);
    }

    public static ClienteDTO from(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.id = cliente.getId();
        dto.nome = cliente.getNome();
        dto.sobrenome = cliente.getSobrenome();
        dto.dataNascimento = cliente.getDataNascimento();
        dto.cpf = cliente.getCpf();
        dto.genero = cliente.getGenero();
        dto.ativo = cliente.isAtivo();
        dto.email = cliente.getUsuario().getEmail();
        dto.senha = cliente.getUsuario().getSenha();

//        dto.telefones = cliente.getTelefones().stream().map(TelefoneDTO::from).collect(Collectors.toList());
        List<TelefoneDTO> telefoneDTOS = new ArrayList<>();
        for (Telefone telefone : cliente.getTelefones()) {
            if (telefone.isAtivo()) {
                telefoneDTOS.add(TelefoneDTO.from(telefone));
            }
        }
        dto.telefones = telefoneDTOS;

//        dto.enderecos = cliente.getEnderecos().stream().map(EnderecoDTO::from).collect(Collectors.toList());
        List<EnderecoDTO> enderecoDTOS = new ArrayList<>();
        for (Endereco endereco : cliente.getEnderecos()) {
            if (endereco.isAtivo()) {
                enderecoDTOS.add(EnderecoDTO.from(endereco));
            }
        }
        dto.enderecos = enderecoDTOS;

        return dto;
    }

}
