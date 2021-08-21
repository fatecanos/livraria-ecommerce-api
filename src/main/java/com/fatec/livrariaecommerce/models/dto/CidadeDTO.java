package com.fatec.livrariaecommerce.models.dto;

import com.fatec.livrariaecommerce.models.domain.Cidade;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CidadeDTO {

    private int id;
    private String nome;

    public CidadeDTO(Cidade cidade) {
        this.id = cidade.getId();
        this.nome = cidade.getNome();
    }

    public static CidadeDTO from (Cidade cidade){
        CidadeDTO dto = new CidadeDTO();
        dto.id = cidade.getId();
        dto.nome = cidade.getNome();
        return dto;
    }

}
