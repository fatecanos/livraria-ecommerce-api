package com.fatec.livrariaecommerce.models.domain;

import javax.persistence.*;

@Entity
@Table
public class Dimensoes {
    @Id
    @SequenceGenerator(
            name="dimensoes_sequence",
            sequenceName="clientes_sequence",
            allocationSize=1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "dimensoes_sequence"
    )
    private int id;
    private String altura;
    private String largura;
    private String peso;
    private String profundidade;

    public Dimensoes() {
    }

    public Dimensoes(int id, String altura, String largura, String peso, String profundidade) {
        this.id = id;
        this.altura = altura;
        this.largura = largura;
        this.peso = peso;
        this.profundidade = profundidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getLargura() {
        return largura;
    }

    public void setLargura(String largura) {
        this.largura = largura;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getProfundidade() {
        return profundidade;
    }

    public void setProfundidade(String profundidade) {
        this.profundidade = profundidade;
    }
}
