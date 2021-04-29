package com.fatec.livrariaecommerce.models.dto;

import lombok.Data;

@Data
public class DimensaoDTO {
    private int id;
    private String altura;
    private String largura;
    private String peso;
    private String profundidade;
}
