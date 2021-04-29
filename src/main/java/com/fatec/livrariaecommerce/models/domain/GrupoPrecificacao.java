package com.fatec.livrariaecommerce.models.domain;


import javax.persistence.*;

@Entity
@Table
public class GrupoPrecificacao {
    @Id
    @SequenceGenerator(
            name="clientes_sequence",
            sequenceName="grupo_precificacao_seq",
            allocationSize=1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "grupo_precificacao_seq"
    )
    private int id;
    private String descricao;

    public GrupoPrecificacao() {
    }

    public GrupoPrecificacao(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
