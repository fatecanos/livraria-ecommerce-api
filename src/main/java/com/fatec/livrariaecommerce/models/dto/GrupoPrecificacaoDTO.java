package com.fatec.livrariaecommerce.models.dto;

import com.fatec.livrariaecommerce.models.domain.GrupoPrecificacao;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GrupoPrecificacaoDTO {

    private int id;
    private String descricao;
    private String nome;
    private Double margem;
    private Double taxa;

    public static GrupoPrecificacaoDTO from(GrupoPrecificacao grupoPrecificacao){
        GrupoPrecificacaoDTO dto = new GrupoPrecificacaoDTO();
        dto.id = grupoPrecificacao.getId();
        dto.descricao = grupoPrecificacao.getDescricao();
        dto.nome = grupoPrecificacao.getNome();
        dto.margem = grupoPrecificacao.getMargem();
        dto.taxa = grupoPrecificacao.getTaxa();
        return dto;
    }

}
