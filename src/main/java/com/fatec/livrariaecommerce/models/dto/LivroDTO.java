package com.fatec.livrariaecommerce.models.dto;

import com.fatec.livrariaecommerce.models.domain.Categoria;
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
}
