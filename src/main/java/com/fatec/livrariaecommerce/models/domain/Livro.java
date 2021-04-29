package com.fatec.livrariaecommerce.models.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table
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
        super(timeStamp, isAtivo);
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(String numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Dimensoes getDimensoes() {
        return dimensoes;
    }

    public void setDimensoes(Dimensoes dimensoes) {
        this.dimensoes = dimensoes;
    }

    public GrupoPrecificacao getGrupoPrecificacao() {
        return grupoPrecificacao;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setGrupoPrecificacao(GrupoPrecificacao grupoPrecificacao) {
        this.grupoPrecificacao = grupoPrecificacao;
    }

    @Override
    public LocalDate getTimeStamp() {
        return this.timeStamp;
    }

    @Override
    public void setTimeStamp(LocalDate timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public boolean isAtivo() {
        return this.isAtivo;
    }

    @Override
    public void setAtivo(boolean ativo) {
        this.isAtivo = ativo;
    }
}
