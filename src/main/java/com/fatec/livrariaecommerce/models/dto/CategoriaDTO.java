package com.fatec.livrariaecommerce.models.dto;

import com.fatec.livrariaecommerce.models.domain.Categoria;
import lombok.NoArgsConstructor;

@NoArgsConstructor

public class CategoriaDTO {
    public int id;
    public String descricao;


    public void fill(Categoria dominio){
        dominio.atualizarDados(this.id, this.descricao);
    }

    static public CategoriaDTO from(Categoria categoria){
        CategoriaDTO dto = new CategoriaDTO();
        dto.id = categoria.getId();
        dto.descricao = categoria.getDescricao();
        return dto;
    }
}
