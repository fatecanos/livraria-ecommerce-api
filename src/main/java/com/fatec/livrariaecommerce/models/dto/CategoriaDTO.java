package com.fatec.livrariaecommerce.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

public class CategoriaDTO {
    public int id;
    public String descricao;

    public CategoriaDTO(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }
}
