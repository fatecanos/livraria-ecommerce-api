package com.fatec.livrariaecommerce.strategy.itenspedido;

import com.fatec.livrariaecommerce.dao.NotificacaoDao;
import com.fatec.livrariaecommerce.models.domain.EntidadeDominio;
import com.fatec.livrariaecommerce.models.domain.ItensPedido;
import com.fatec.livrariaecommerce.models.domain.Notificacao;
import com.fatec.livrariaecommerce.models.domain.StatusPedido;
import com.fatec.livrariaecommerce.strategy.IStrategy;

public class SolicitarTroca implements IStrategy {

    public final NotificacaoDao notificacaoDao;

    public SolicitarTroca(NotificacaoDao notificacaoDao) {
        this.notificacaoDao = notificacaoDao;
    }

    @Override
    public String processar(EntidadeDominio dominio) {
        ItensPedido itensPedido = (ItensPedido) dominio;
        if (itensPedido.getStatusPedido() == StatusPedido.ENTREGUE) {

            if (itensPedido.getQuantidadeTrocada() > itensPedido.getQtdComprada()) {
                return "Quantidade solicitada para troca é maior que a quantidade comprada." +
                        " Tenta novamente com um valor menor.";
            } else {
                itensPedido.setStatusPedido(StatusPedido.TROCA_SOLICITADA);
            }
        }

        if (itensPedido.getStatusPedido() == StatusPedido.TROCA_RECUSADA) {
            Notificacao notificacao = new Notificacao(itensPedido.getVenda().getCliente(), itensPedido);
            notificacao.atualizarDados(0, "A solicitação de troca do item " + itensPedido.getNomeLivro()
                    + " do seu pedido de número "
                    + itensPedido.getVenda().getNumero() + " foi recusada.", false);
            this.notificacaoDao.salvar(notificacao);
            itensPedido.setStatusPedido(StatusPedido.TROCA_RECUSADA);
        }

        if (itensPedido.getStatusPedido() == StatusPedido.TROCA_ACEITA) {
            Notificacao notificacao = new Notificacao(itensPedido.getVenda().getCliente(), itensPedido);
            notificacao.atualizarDados(0, "A solicitação de troca do item " + itensPedido.getNomeLivro()
                    + " do seu pedido de número "
                    + itensPedido.getVenda().getNumero() + " foi aceita.", false);
            this.notificacaoDao.salvar(notificacao);
            itensPedido.setStatusPedido(StatusPedido.EM_TROCA);
        }
        return "";

    }
}
