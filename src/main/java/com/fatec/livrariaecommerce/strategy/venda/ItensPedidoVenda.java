package com.fatec.livrariaecommerce.strategy.venda;

import com.fatec.livrariaecommerce.dao.ItensPedidoDao;
import com.fatec.livrariaecommerce.models.domain.EntidadeDominio;
import com.fatec.livrariaecommerce.models.domain.ItensPedido;
import com.fatec.livrariaecommerce.models.domain.Venda;
import com.fatec.livrariaecommerce.strategy.IStrategy;

public class ItensPedidoVenda implements IStrategy {

    private final ItensPedidoDao itensPedidoDao;

    public ItensPedidoVenda(ItensPedidoDao itensPedidoDao) {
        this.itensPedidoDao = itensPedidoDao;
    }

    @Override
    public String processar(EntidadeDominio dominio) {
        Venda venda = (Venda) dominio;
        ItensPedido itensPedido = new ItensPedido();
//        itensPedido.setVenda(venda);
        itensPedido.setId(10);


//        itensPedido = (ItensPedido) this.itensPedidoDao.consultarItensPorVenda(itensPedido);
//        this.itensPedidoDao.consultarItensPorVenda(itensPedido);
        this.itensPedidoDao.consultar(itensPedido);

        System.out.println("Me diz aqui esse cara: " + itensPedido);


        return "";
    }
}
