package com.fatec.livrariaecommerce.models.dto;

import com.fatec.livrariaecommerce.models.domain.FaturamentoLivroLinha;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FaturamentoLivroLinhaDTO {

    private int idLivro;
    private String nomeLivro;
    private double faturamento;

    public static FaturamentoLivroLinhaDTO from(FaturamentoLivroLinha faturamentoLivroLinha) {
        FaturamentoLivroLinhaDTO dto = new FaturamentoLivroLinhaDTO();

        dto.idLivro = faturamentoLivroLinha.getIdLivro();
        dto.nomeLivro = faturamentoLivroLinha.getNomeLivro();
        dto.faturamento = faturamentoLivroLinha.getFaturamento();
        return dto;
    }

}
