package com.fatec.livrariaecommerce.models.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor

@Entity
@Table
public class GrupoPrecificacao extends EntidadeDominio {

    private String descricao;
    private String nome;
    private Double margem;
    private Double taxa;

    public void atualizarDados(int id, String descricao, String nome, Double margem, Double taxa) {
        super.setId(id);
        super.setAtivo(true);
        if (this.getId() == 0 || this.getId() == null) {
            super.setDataCriacao(LocalDateTime.now());
        }
        this.descricao = descricao;
        this.nome = nome;
        this.margem = margem;
        this.taxa = taxa;
    }
}
