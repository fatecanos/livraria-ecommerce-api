package com.fatec.livrariaecommerce.negocio.venda;

import com.fatec.livrariaecommerce.dao.VendaDao;
import com.fatec.livrariaecommerce.models.domain.EntidadeDominio;
import com.fatec.livrariaecommerce.models.domain.StatusVenda;
import com.fatec.livrariaecommerce.models.domain.Venda;
import com.fatec.livrariaecommerce.negocio.IStrategy;

public class AlteraStatusVenda implements IStrategy {

    public final VendaDao vendaDao;

    public AlteraStatusVenda(VendaDao vendaDao) {
        this.vendaDao = vendaDao;
    }


    @Override
    public String processar(EntidadeDominio dominio) {
        Venda venda = (Venda) this.vendaDao.consultar(dominio).get(0);

        StatusVenda[] statusVenda = StatusVenda.values();

       if(!venda.isCancelarVenda()){
           if (venda.getStatusVenda() != StatusVenda.ENTREGUE) {
               if (venda.getStatusVenda() != StatusVenda.PEDIDO_CANCELADO) {
                   for (int i = 0; i < statusVenda.length; i++) {
                       if (venda.getStatusVenda() == statusVenda[i]) {
                           System.out.println("Antes " + venda.getStatusVenda());
                           venda.setStatusVenda(StatusVenda.values()[i + 1]);
                           System.out.println("Depois " + venda.getStatusVenda());
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
