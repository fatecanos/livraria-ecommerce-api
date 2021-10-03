package com.fatec.livrariaecommerce.models.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter

@NoArgsConstructor

@Embeddable
public class Dimensoes {
    private double altura;
    private double largura;
    private double peso;
    private double profundidade;

    public Dimensoes(double altura, double largura, double peso, double profundidade) {
        this.altura = altura;
        this.largura = largura;
        this.peso = peso;
        this.profundidade = profundidade;
    }
}
