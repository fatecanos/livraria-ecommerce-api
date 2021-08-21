package com.fatec.livrariaecommerce.models.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor

@Setter
@Getter
@Entity
@Table(name = "cliente")
public class Cliente extends EntidadeDominio {

    // ***********************************************************************

//    @Id
//    @SequenceGenerator(name = "clientes_sequence", sequenceName = "clientes_sequence", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clientes_sequence")
//    private int id;
    private String nome;
    private String sobrenome;
    private LocalDate dataNascimento;
    private String cpf;
    private String genero;

    @OneToOne(cascade = CascadeType.ALL)
    private Usuario usuario;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private List<Endereco> enderecos;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private List<Telefone> telefones;

    // ***********************************************************************

    public void atualizarDados(int id, String nome, String sobrenome, LocalDate dataNascimento, String cpf,
                               String genero, Usuario usuario) {
        super.setId(id);
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.genero = genero;
        this.usuario = usuario;
    }


}
