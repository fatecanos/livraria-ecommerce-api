package com.fatec.livrariaecommerce.negocio.venda;

import com.fatec.livrariaecommerce.dao.VendaDao;
import com.fatec.livrariaecommerce.models.domain.EntidadeDominio;
import com.fatec.livrariaecommerce.models.domain.StatusVenda;
import com.fatec.livrariaecommerce.models.domain.Venda;
import com.fatec.livrariaecommerce.negocio.IStrategy;

public class CancelarVenda implements IStrategy {

    @Override
    public String processar(EntidadeDominio dominio) {
        Venda venda = (Venda) dominio;

        if (venda.isCancelarVenda()) {
            if (venda.getStatusVenda() != StatusVenda.PEDIDO_CANCELADO) {
                if (venda.getStatusVenda() != StatusVenda.ENTREGUE) {
                    if (venda.getStatusVenda() == StatusVenda.EM_PROCESSAMENTO
                            || venda.getStatusVenda() == StatusVenda.PAGAMENTO_REALIZADO) {
                        venda.setStatusVenda(StatusVenda.PEDIDO_CANCELADO);
                    } else {
                        return "Pedido já saiu para entrega. Impossível cancelar.";
                    }
                } else {
                    return "Pedido já entregue. Solicite devolução.";
                }
            } else {
                return "Pedido já cancelado.";
            }


        }


        return "";
    }
}
