package com.fatec.livrariaecommerce.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class LivroFaturamentoParamDTO {

    @JsonProperty
    private int idLivro;
    @JsonProperty
    private String dataInicio;
    @JsonProperty
    private String dataFim;

}
