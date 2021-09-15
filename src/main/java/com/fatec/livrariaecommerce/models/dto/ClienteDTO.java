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
    private List<CartaoCreditoDTO> cartoesCredito;

    public void fill(Cliente dominio) {
        Usuario usuario;
        if (dominio.getUsuario() == null) {
            usuario = new Usuario(this.getEmail(), this.getSenha(), PerfilUsuario.CLIENTE);
        } else {
            usuario = dominio.getUsuario();
        }
        List<Endereco> enderecoList = new ArrayList<>();
        List<Telefone> telefoneList = new ArrayList<>();

        if (!this.getEnderecos().isEmpty()) {
            Endereco endereco = new Endereco(dominio);
            for (EnderecoDTO enderecoDTO : this.getEnderecos()) {
                enderecoDTO.fill(endereco);
            }
            enderecoList.add(endereco);
        }

        if (!this.getTelefones().isEmpty()) {
            Telefone telefone = new Telefone(dominio);
            for (TelefoneDTO telefoneDTO : this.getTelefones()) {
                telefoneDTO.fill(telefone);
            }
            telefoneList.add(telefone);
        }

        dominio.atualizarDados(this.id, this.nome, this.sobrenome, this.dataNascimento, this.cpf,
                this.genero, enderecoList, telefoneList, usuario);
    }

    public static ClienteDTO from(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.id = cliente.getId();
        dto.nome = cliente.getNome();
        dto.sobrenome = cliente.getSobrenome();
        dto.dataNascimento = cliente.getDataNascimento();
        dto.cpf = cliente.getCpf();
        dto.genero = cliente.getGenero();
        dto.ativo = cliente.getAtivo();
        dto.email = cliente.getUsuario().getEmail();
        dto.senha = cliente.getUsuario().getSenha();
        dto.telefones = cliente.getTelefones().stream().map(TelefoneDTO::from).collect(Collectors.toList());
        dto.enderecos = cliente.getEnderecos().stream().map(EnderecoDTO::from).collect(Collectors.toList());
        dto.cartoesCredito = cliente.getCartoesCredito().stream().map(CartaoCreditoDTO::from).collect(Collectors.toList());
        return dto;
    }

}
