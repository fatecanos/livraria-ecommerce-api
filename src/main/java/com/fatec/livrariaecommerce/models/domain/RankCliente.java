package com.fatec.livrariaecommerce.models.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RankCliente {

    private Integer idCliente;
    private String nomeCliente;
    private String cpfCliente;
    private int comprasRealizadas;
}
