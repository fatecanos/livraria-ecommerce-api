package com.fatec.livrariaecommerce.models.dto;

import com.fatec.livrariaecommerce.models.domain.Categoria;
import com.fatec.livrariaecommerce.models.domain.Livro;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class LivroDTO {
    private int id;
    private String titulo;
    private String autor;
    private List<Categoria> categorias;
    private String ano;
    private String editora;
    private String edicao;
    private String isbn;
    private String quantidadePaginas;
    private String sinopse;
    private double altura;
    private double largura;
    private double peso;
    private double profundidade;
    private int grupoPrecificacaoId;
    private String codigoBarras;
    private String url;
    private boolean isAtivo;
    private LocalDate timestamp;

    public LivroDTO(Livro livro) {
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
        this.autor = livro.getAutor();
        this.categorias = livro.getCategorias();
        this.ano = livro.getAno();
        this.editora = livro.getEditora();
        this.edicao = livro.getEdicao();
        this.isbn = livro.getIsbn();
        this.quantidadePaginas = livro.getNumeroPaginas();
        this.sinopse = livro.getSinopse();
        this.altura = livro.getAltura();
        this.largura = livro.getLargura();
        this.peso = livro.getPeso();
        this.profundidade = livro.getProfundidade();
        this.grupoPrecificacaoId = livro.getGrupoPrecificacao().getId();
        this.codigoBarras = livro.getCodigoBarras();
        this.url = livro.getUrl();
        this.isAtivo = livro.isAtivo();
    }
}
