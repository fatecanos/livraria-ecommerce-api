package com.fatec.livrariaecommerce.strategy.itenspedido;

import com.fatec.livrariaecommerce.models.domain.*;
import com.fatec.livrariaecommerce.strategy.IStrategy;

import java.time.LocalDateTime;

public class GerarCupom implements IStrategy {

    @Override
    public String processar(EntidadeDominio dominio) {
        ItensPedido itensPedido = (ItensPedido) dominio;

        if (itensPedido.getStatusPedido() == StatusPedido.TROCA_AUTORIZADA) {
            Cupom cupom = new Cupom();
            cupom.setAtivo(true);
            cupom.setDataCriacao(LocalDateTime.now());
            cupom.setNome("Cupom troca");
            cupom.setValor(itensPedido.getValorTotal());
            cupom.setCliente(itensPedido.getVenda().getCliente());
            cupom.setItensPedido(itensPedido);
            cupom.setTipoCupom(TipoCupom.TROCA);
            itensPedido.setCupomGerado(cupom);
            return "";
        }
        return "";
    }
}
