package com.fatec.livrariaecommerce.models.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor

@Setter
@Getter
@Entity
@Table(name = "cliente")
public class Cliente extends EntidadeDominio {

    // ***********************************************************************

    private String nome;
    private String sobrenome;
    private LocalDate dataNascimento;
    private String cpf;
    private String genero;

    @OneToOne(cascade = CascadeType.ALL)
    private Usuario usuario;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    @Where(clause = "ativo = true")
    @OrderBy(value = "id")
    private List<Endereco> enderecos;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    @Where(clause = "ativo = true")
    @OrderBy(value = "id")
    private List<Telefone> telefones;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    @Where(clause = "ativo = true")
    @OrderBy(value = "id")
    private List<CartaoCredito> cartoesCredito;

    // ***********************************************************************


    public void atualizarDados(int id, String nome, String sobrenome, LocalDate dataNascimento, String cpf,
                               String genero, List<Endereco> enderecos, List<Telefone> telefones, Usuario usuario) {

        super.setId(id);
        super.setAtivo(true);
        if (this.getId() == 0 || this.getId() == null) {
            super.setDataCriacao(LocalDateTime.now());
        }
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.genero = genero;
        this.usuario = usuario;
        this.enderecos = enderecos;
        this.telefones = telefones;
//        this.cartoesCredito = cartoesCredito;
    }

    public void atualizarDados(int id, String nome, String sobrenome, LocalDate dataNascimento, String cpf,
                               String genero, Usuario usuario) {
        super.setId(id);
        super.setAtivo(true);
        if (this.getId() == 0 || this.getId() == null) {
            super.setDataCriacao(LocalDateTime.now());
        }
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.genero = genero;
        this.usuario = usuario;
    }


}
