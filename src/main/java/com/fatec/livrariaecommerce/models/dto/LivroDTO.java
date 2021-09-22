package com.fatec.livrariaecommerce.models.dto;

import com.fatec.livrariaecommerce.models.domain.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class LivroDTO {
    private int id;
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
    private double valorVenda;
    private double valorCompra;
    private int estoque;
    private DimensaoDTO dimensoes;
    private GrupoPrecificacao grupoPrecificacao;
    private boolean ativo;
    private List<CategoriaDTO> categorias;

    public void fill(Livro dominio) {

        List<Categoria> categoriaList = new ArrayList<>();

        Dimensoes dimensoes;
        if (dominio.getDimensoes() == null) {
            dimensoes = new Dimensoes(this.getDimensoes().getAltura(), this.getDimensoes().getLargura(),
                    this.getDimensoes().getPeso(), this.getDimensoes().getProfundidade());
        } else {
            dimensoes = dominio.getDimensoes();
        }

        if (!this.getCategorias().isEmpty()) {
            Categoria categoria = new Categoria();
            for (CategoriaDTO categoriaDTO : this.getCategorias()) {
                categoriaDTO.fill(categoria);
            }
            categoriaList.add(categoria);
        }

        dominio.atualizarDados(this.id, this.titulo, this.autor, this.ano, this.editora, this.edicao, this.isbn,
                this.numeroPaginas, this.sinopse, this.url, this.codigoBarras, this.valorCompra, this.valorVenda, this.estoque,
                dimensoes, this.grupoPrecificacao, categoriaList);
    }

    public static LivroDTO from(Livro livro) {
        LivroDTO dto = new LivroDTO();

        dto.id = livro.getId();
        dto.titulo = livro.getTitulo();
        dto.autor = livro.getAutor();
        dto.ano = livro.getAno();
        dto.editora = livro.getEditora();
        dto.edicao = livro.getEdicao();
        dto.isbn = livro.getIsbn();
        dto.numeroPaginas = livro.getNumeroPaginas();
        dto.sinopse = livro.getSinopse();
        dto.url = livro.getUrl();
        dto.codigoBarras = livro.getCodigoBarras();
        dto.valorCompra = livro.getValorCompra();
        dto.valorVenda = livro.getValorVenda();
        dto.estoque = livro.getEstoque();
        dto.dimensoes = DimensaoDTO.from(livro.getDimensoes());
        dto.grupoPrecificacao = livro.getGrupoPrecificacao();
        dto.categorias = livro.getCategorias().stream().map(CategoriaDTO::from).collect(Collectors.toList());;
        return dto;
    }
}
