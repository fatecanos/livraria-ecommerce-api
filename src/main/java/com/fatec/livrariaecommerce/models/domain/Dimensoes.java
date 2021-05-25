package com.fatec.livrariaecommerce.models.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Dimensoes {
    @Id
    @SequenceGenerator(
            name="dimensoes_sequence",
            sequenceName="dimensoes_sequence",
            allocationSize=1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "dimensoes_sequence"
    )
    private int id;
    private double altura;
    private double largura;
    private double peso;
    private double profundidade;

}
