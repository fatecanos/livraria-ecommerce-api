package com.fatec.livrariaecommerce.models.dto;

import com.fatec.livrariaecommerce.models.domain.FaturamentoMensal;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FaturamentoMensalDTO {

    private String data;
    private double faturamento;

    public static FaturamentoMensalDTO from(FaturamentoMensal faturamentoMensal){
        FaturamentoMensalDTO dto = new FaturamentoMensalDTO();
        dto.data = faturamentoMensal.getData();
        dto.faturamento = faturamentoMensal.getFaturamento();
        return dto;
    }

}
