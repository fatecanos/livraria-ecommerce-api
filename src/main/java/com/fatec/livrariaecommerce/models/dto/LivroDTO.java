package com.fatec.livrariaecommerce.models.dto;

import com.fatec.livrariaecommerce.models.domain.Categoria;
import com.fatec.livrariaecommerce.models.domain.Livro;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
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
    private DimensaoDTO dimensoes;
    private int grupoPrecificacaoId;
    private String codigoBarras;
    private String url;
    private boolean isAtivo;

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
        //this.dimensoes =
        this.grupoPrecificacaoId = livro.getGrupoPrecificacao().getId();
        this.codigoBarras = livro.getCodigoBarras();
        this.url = livro.getUrl();
        this.isAtivo = livro.isAtivo();


    }
}
