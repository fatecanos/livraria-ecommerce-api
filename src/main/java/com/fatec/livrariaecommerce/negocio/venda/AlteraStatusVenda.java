package com.fatec.livrariaecommerce.negocio.venda;

import com.fatec.livrariaecommerce.dao.VendaDao;
import com.fatec.livrariaecommerce.models.domain.*;
import com.fatec.livrariaecommerce.negocio.IStrategy;

public class AlteraStatusVenda implements IStrategy {

    public final VendaDao vendaDao;

    // ***********************************************************************

    public AlteraStatusVenda(VendaDao vendaDao) {
        this.vendaDao = vendaDao;
    }

    // ***********************************************************************

    @Override
    public String processar(EntidadeDominio dominio) {
        Venda venda = (Venda) this.vendaDao.consultarSomenteID(dominio).get(0);
        StatusVenda[] statusVenda = StatusVenda.values();
        if (!venda.isCancelarVenda()) {
            if (venda.getStatusVenda() != StatusVenda.ENTREGUE) {
                if (venda.getStatusVenda() != StatusVenda.PEDIDO_CANCELADO) {
                    for (int i = 0; i < statusVenda.length; i++) {
                        if (venda.getStatusVenda() == statusVenda[i]) {
                            venda.setStatusVenda(StatusVenda.values()[i + 1]);
                            if (StatusVenda.values()[i + 1] == StatusVenda.ENTREGUE) {
                                for (ItensPedido itens : venda.getItensPedidos()) {
                                    itens.setStatusPedido(StatusPedido.ENTREGUE);
                                }
                            }
                            return "";
                        }
                    }
                } else {
                    return "O pedido foi cancelado!";
                }
            } else {
                return "O pedido já foi entregue!";
            }
        }
        return "";
    }
}
