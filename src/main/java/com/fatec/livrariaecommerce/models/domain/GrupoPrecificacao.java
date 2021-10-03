package com.fatec.livrariaecommerce.models.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter

@Entity
@Table
public class GrupoPrecificacao extends EntidadeDominio{

    private String nome;
    private String descricao;
    private Double margem;

}
