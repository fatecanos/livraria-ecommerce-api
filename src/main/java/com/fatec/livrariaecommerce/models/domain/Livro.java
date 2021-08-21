package com.fatec.livrariaecommerce.models.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter

@Entity
@Table
public class Livro extends EntidadeDominio {
//    @Id
//    @SequenceGenerator(
//            name="livros_sequence",
//            sequenceName="clientes_sequence",
//            allocationSize=1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "livros_sequence"
//    )
//    private int id;
    private String autor;
    private String ano;
    private String titulo;
    private String editora;
    private String edicao;
    private String isbn;
    private String numeroPaginas;
    private String sinopse;
    private String url;
    private LocalDate timeStamp;
    private boolean isAtivo;

    @ManyToMany
    @JoinTable
    private List<Categoria> categorias;

    @OneToOne(cascade = CascadeType.ALL)
    private Dimensoes dimensoes;

    @OneToOne(cascade = CascadeType.ALL)
    private GrupoPrecificacao grupoPrecificacao;

    public Livro() {
    }

    public Livro(LocalDate timeStamp, boolean isAtivo, int id, String autor, String ano, String titulo, String editora, String edicao, String isbn, String numeroPaginas, String sinopse, String url, List<Categoria> categorias, Dimensoes dimensoes, GrupoPrecificacao grupoPrecificacao) {
        super();
//        this.id = id;
        this.autor = autor;
        this.ano = ano;
        this.titulo = titulo;
        this.editora = editora;
        this.edicao = edicao;
        this.isbn = isbn;
        this.numeroPaginas = numeroPaginas;
        this.sinopse = sinopse;
        this.url = url;
        this.categorias = categorias;
        this.dimensoes = dimensoes;
        this.grupoPrecificacao = grupoPrecificacao;
    }

}
