package com.fatec.livrariaecommerce.models.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor

@Entity
@Table
public class GrupoPrecificacao extends EntidadeDominio{

    private String descricao;
    private String nome;
    private Double margem;
    private Double taxa;

}
