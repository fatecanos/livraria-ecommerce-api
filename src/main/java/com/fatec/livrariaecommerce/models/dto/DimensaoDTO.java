package com.fatec.livrariaecommerce.models.dto;

import com.fatec.livrariaecommerce.models.domain.Dimensoes;
import lombok.Data;

@Data
public class DimensaoDTO {
    private double altura;
    private double largura;
    private double peso;
    private double profundidade;

    public static DimensaoDTO from(Dimensoes dimensoes){
        DimensaoDTO dto = new DimensaoDTO();
        dto.altura = dimensoes.getAltura();
        dto.largura = dimensoes.getLargura();
        dto.peso = dimensoes.getPeso();
        dto.profundidade = dimensoes.getProfundidade();
        return dto;
    }
}
