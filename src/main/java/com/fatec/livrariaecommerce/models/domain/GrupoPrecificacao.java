package com.fatec.livrariaecommerce.models.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter



@Entity
@Table
public class GrupoPrecificacao extends EntidadeDominio{
//    @Id
//    @SequenceGenerator(
//            name="clientes_sequence",
//            sequenceName="grupo_precificacao_seq",
//            allocationSize=1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "grupo_precificacao_seq"
//    )
//    private int id;
    private String descricao;

    public GrupoPrecificacao() {
    }

    public GrupoPrecificacao(int id, String descricao) {
//        this.id = id;
        this.descricao = descricao;
    }
}
