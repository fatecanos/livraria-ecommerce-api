package com.fatec.livrariaecommerce.models.dto;

import com.fatec.livrariaecommerce.models.domain.Dimensoes;
import lombok.Data;

@Data
public class DimensaoDTO {
    private int id;
    private double altura;
    private double largura;
    private double peso;
    private double profundidade;

    public DimensaoDTO(Dimensoes dimensao) {
        this.id = dimensao.getId();
        this.altura = dimensao.getAltura();
        this.largura = dimensao.getLargura();
        this.peso = dimensao.getPeso();
        this.profundidade = dimensao.getProfundidade();
    }
}
