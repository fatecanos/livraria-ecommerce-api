package com.fatec.livrariaecommerce.models.dto;

import com.fatec.livrariaecommerce.models.domain.Cupom;
import com.fatec.livrariaecommerce.models.domain.TipoCupom;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CupomDTO {

    private int id;
    private String nome;
    private double valor;
    private TipoCupom tipoCupom;

    public void fill(Cupom dominio){
        dominio.atualizarDados(this.id, this.nome, this.valor, this.tipoCupom);
    }

    public static CupomDTO from(Cupom dominio){
        CupomDTO dto = new CupomDTO();

        dto.id = dominio.getId();
        dto.nome = dominio.getNome();
        dto.valor = dominio.getValor();
        dto.tipoCupom = dominio.getTipoCupom();
        return dto;
    }

}
