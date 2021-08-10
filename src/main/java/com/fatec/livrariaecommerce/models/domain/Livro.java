package com.fatec.livrariaecommerce.models.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Livro extends EntidadeDominio {
    @Id
    @SequenceGenerator(
            name="livros_sequence",
            sequenceName="clientes_sequence",
            allocationSize=1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "livros_sequence"
    )
    private int id;
    private LocalDate timeStamp;
    private boolean isAtivo;

    private String autor;
    private String ano;
    private String titulo;
    private String editora;
    private String edicao;
    private String isbn;
    private String numeroPaginas;
    private String sinopse;
    private String codigoBarras;
    private String url;

    @ManyToMany
    @JoinTable
    private List<Categoria> categorias;

    @OneToOne(cascade = CascadeType.ALL)
    private Dimensoes dimensoes;

    @OneToOne(cascade = CascadeType.ALL)
    private GrupoPrecificacao grupoPrecificacao;
}
