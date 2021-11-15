package com.fatec.livrariaecommerce.models.dto;

import com.fatec.livrariaecommerce.models.domain.LivroFaturamentoMensal;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LivroFaturamentoMensalDTO {

    private int idLivro;
    private String nomeLivro;
    private String data;
    private double faturamento;

    public static LivroFaturamentoMensalDTO from(LivroFaturamentoMensal livroFaturamentoMensal) {
        LivroFaturamentoMensalDTO dto = new LivroFaturamentoMensalDTO();

        dto.idLivro = livroFaturamentoMensal.getIdLivro();
        dto.nomeLivro = livroFaturamentoMensal.getNomeLivro();
        dto.data = livroFaturamentoMensal.getData();
        dto.faturamento = livroFaturamentoMensal.getFaturamento();
        return dto;
    }

}
