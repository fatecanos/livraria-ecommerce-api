package com.fatec.livrariaecommerce.strategy.cartao;

import com.fatec.livrariaecommerce.models.domain.CartaoCredito;
import com.fatec.livrariaecommerce.models.domain.EntidadeDominio;
import com.fatec.livrariaecommerce.strategy.IStrategy;

public class SalvarCartaoFuturaCompra implements IStrategy {
    @Override
    public String processar(EntidadeDominio dominio) {
        CartaoCredito cartaoCredito = (CartaoCredito) dominio;
        if(!cartaoCredito.isSalvar()){
            cartaoCredito.setCliente(null);
        }
        return "";
    }
}
