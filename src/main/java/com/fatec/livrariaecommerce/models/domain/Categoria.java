package com.fatec.livrariaecommerce.models.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@Entity
@Table
public class Categoria extends EntidadeDominio {
    private String descricao;

    public void atualizarDados(int id, String descricao){

        super.setId(id);
        this.descricao = descricao;
    }
}
