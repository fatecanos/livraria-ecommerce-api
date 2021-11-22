package com.fatec.livrariaecommerce.strategy.venda;

import com.fatec.livrariaecommerce.models.domain.EntidadeDominio;
import com.fatec.livrariaecommerce.models.domain.FormaPagamento;
import com.fatec.livrariaecommerce.models.domain.Venda;
import com.fatec.livrariaecommerce.strategy.IStrategy;

public class ValidaValorPagoCartoes implements IStrategy {
    @Override
    public String processar(EntidadeDominio dominio) {
        Venda venda = (Venda) dominio;

        if(venda.getFormaPagamentoList().size() >= 2){
            for(FormaPagamento fmp : venda.getFormaPagamentoList()){
                if(fmp.getValorPago() < 10){
                    return "O valor mínimo a ser pago com cada cartão é de: R$10,00. Tente novamente.";
                }
            }
        }
        return "";
    }
}
