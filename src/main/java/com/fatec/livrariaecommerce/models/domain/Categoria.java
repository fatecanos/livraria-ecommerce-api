package com.fatec.livrariaecommerce.models.domain;

import javax.persistence.*;

@Entity
@Table
public class Categoria {
    @Id
    @SequenceGenerator(
            name="categorias_sequence",
            sequenceName="clientes_sequence",
            allocationSize=1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "categorias_sequence"
    )
    private int id;
    private String descricao;

    public Categoria() {
    }

    public Categoria(int id, String descricao) {
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
