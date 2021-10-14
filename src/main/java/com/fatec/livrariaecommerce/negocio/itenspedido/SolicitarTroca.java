package com.fatec.livrariaecommerce.negocio.itenspedido;

import com.fatec.livrariaecommerce.models.domain.EntidadeDominio;
import com.fatec.livrariaecommerce.models.domain.ItensPedido;
import com.fatec.livrariaecommerce.models.domain.StatusPedido;
import com.fatec.livrariaecommerce.models.domain.StatusVenda;
import com.fatec.livrariaecommerce.negocio.IStrategy;

public class SolicitarTroca implements IStrategy {

    @Override
    public String processar(EntidadeDominio dominio) {
        ItensPedido itensPedido = (ItensPedido) dominio;
        if (itensPedido.getStatusPedido() == StatusPedido.ENTREGUE) {
            itensPedido.setStatusPedido(StatusPedido.TROCA_SOLICITADA);
        }

        if (itensPedido.getStatusPedido() == StatusPedido.TROCA_RECUSADA) {
            return "Sinto muito, a solicitação de troca do seu pedido foi recusada.";
        }

        if (itensPedido.getStatusPedido() == StatusPedido.TROCA_ACEITA) {
            itensPedido.setStatusPedido(StatusPedido.EM_TROCA);
        }
        return "";

    }
}
