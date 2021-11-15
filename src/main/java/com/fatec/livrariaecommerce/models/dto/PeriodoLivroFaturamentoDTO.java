package com.fatec.livrariaecommerce.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PeriodoLivroFaturamentoDTO {

    @JsonProperty
    private List<LivroIdDTO> idsLivros;

    @JsonProperty
    private String dataInicio;
    @JsonProperty
    private String dataFim;


}


