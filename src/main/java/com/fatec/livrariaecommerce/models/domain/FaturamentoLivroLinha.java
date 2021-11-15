package com.fatec.livrariaecommerce.models.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class FaturamentoLivroLinha {
    private int idLivro;
    private String nomeLivro;
    private double faturamento;
}
