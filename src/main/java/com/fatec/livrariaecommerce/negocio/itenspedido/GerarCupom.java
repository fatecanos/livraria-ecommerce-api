package com.fatec.livrariaecommerce.negocio.itenspedido;

import com.fatec.livrariaecommerce.models.domain.*;
import com.fatec.livrariaecommerce.negocio.IStrategy;

public class GerarCupom implements IStrategy {

    @Override
    public String processar(EntidadeDominio dominio) {
        ItensPedido itensPedido = (ItensPedido) dominio;
        if (itensPedido.getStatusPedido() == StatusPedido.TROCA_AUTORIZADA) {
            Cupom cupom = new Cupom();
            cupom.setNome("Cupom troca");
            cupom.setValor(itensPedido.getValorTotal());
            cupom.setCliente(itensPedido.getVenda().getCliente());
            cupom.setItensPedido(itensPedido);
            cupom.setTipoCupom(TipoCupom.TROCA);
            itensPedido.setCupomGerado(cupom);
        }
        return "";
    }
}
