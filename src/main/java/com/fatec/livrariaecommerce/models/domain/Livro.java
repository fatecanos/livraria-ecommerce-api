package com.fatec.livrariaecommerce.models.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor

@Entity
@Table
public class Livro extends EntidadeDominio {
    private String titulo;
    private String autor;
    private String ano;
    private String editora;
    private String edicao;
    private String isbn;
    private String numeroPaginas;
    private String sinopse;
    private String url;
    private String codigoBarras;

    @Column(name = "valor_compra", nullable = false)
    private Double valorCompra;

    @Column(name = "valor_venda", nullable = false)
    private Double valorVenda;

    @Column(name = "estoque", nullable = false)
    private Integer estoque;

//    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
//    @JoinColumn(name = "cidade_id")

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "categoria_id")
    @Where(clause = "ativo = true")
    private List<Categoria> categorias;

    @Embedded
    private Dimensoes dimensoes;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "grupo_precificacao_id")
    private GrupoPrecificacao grupoPrecificacao;

    public void atualizarDados(int id, String titulo, String autor, String ano, String editora, String edicao,
                               String isbn, String numeroPaginas, String sinopse, String url, String codigoBarras, Double valorCompra,
                               Double valorVenda, Integer estoque, Dimensoes dimensoes, GrupoPrecificacao grupoPrecificacao, List<Categoria> categorias
                               ) {

        super.setId(id);
        super.setAtivo(true);
        if (this.getId() == 0 || this.getId() == null) {
            super.setDataCriacao(LocalDateTime.now());
        }
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.editora = editora;
        this.edicao = edicao;
        this.isbn = isbn;
        this.numeroPaginas = numeroPaginas;
        this.sinopse = sinopse;
        this.url = url;
        this.codigoBarras = codigoBarras;
        this.valorCompra = valorCompra;
        this.valorVenda = valorVenda;
        this.estoque = estoque;
        this.categorias = categorias;
        this.dimensoes = dimensoes;
        this.grupoPrecificacao = grupoPrecificacao;
    }
}
