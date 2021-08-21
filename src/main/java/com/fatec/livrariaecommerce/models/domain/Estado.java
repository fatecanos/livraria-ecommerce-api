package com.fatec.livrariaecommerce.models.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "estado")
public class Estado extends EntidadeDominio {
//    @Id
//    @SequenceGenerator(
//            name = "estados_sequences",
//            sequenceName = "estados_sequences",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "estados_sequences"
//    )
//    private int id;
    private String uf;
    private String nome;

    public Estado() {
    }

    public Estado(Estado estado) {
//        this.id = estado.getId();
//        this.descricao = estado.getDescricao();
    }


}
