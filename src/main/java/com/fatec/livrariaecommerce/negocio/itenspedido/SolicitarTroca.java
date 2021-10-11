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
        if(itensPedido.getVenda().getStatusVenda() == StatusVenda.ENTREGUE){
            itensPedido.setStatusPedido(StatusPedido.TROCA_SOLICITADA);
        }else{
            return "Seu pedido ainda não foi entregue. Aguarde a entrega para solicitar a troca.";
        }
        return "";
    }
}
