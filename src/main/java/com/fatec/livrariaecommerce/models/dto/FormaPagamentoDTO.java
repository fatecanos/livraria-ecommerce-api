package com.fatec.livrariaecommerce.models.dto;

import com.fatec.livrariaecommerce.models.domain.FormaPagamento;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;

@Data
@NoArgsConstructor
public class FormaPagamentoDTO {

    private int id;
    @JoinColumn(name = "id_cartao")
    private int idCartao;
    private double valorPago;

    public void fill(FormaPagamento dominio){
        dominio.atualizarDados(this.id, this.idCartao, this.valorPago);
    }

    public static FormaPagamentoDTO from(FormaPagamento dominio){
        FormaPagamentoDTO dto = new FormaPagamentoDTO();
        dto.id = dominio.getId();
        dto.idCartao = dominio.getIdCartao();
        dto.valorPago = dominio.getValorPago();
        return dto;
    }

}
