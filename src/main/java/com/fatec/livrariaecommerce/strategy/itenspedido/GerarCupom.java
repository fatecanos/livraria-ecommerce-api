package com.fatec.livrariaecommerce.strategy.itenspedido;

import com.fatec.livrariaecommerce.dao.LivroDao;
import com.fatec.livrariaecommerce.models.domain.*;
import com.fatec.livrariaecommerce.strategy.IStrategy;

import java.time.LocalDateTime;

public class GerarCupom implements IStrategy {

    public final LivroDao livroDao;

    public GerarCupom(LivroDao livroDao) {
        this.livroDao = livroDao;
    }

    @Override
    public String processar(EntidadeDominio dominio) {
        ItensPedido itensPedido = (ItensPedido) dominio;

        if (itensPedido.getStatusPedido() == StatusPedido.TROCA_AUTORIZADA) {
            Cupom cupom = new Cupom();
            Livro livro = new Livro();

            cupom.setAtivo(true);
            cupom.setDataCriacao(LocalDateTime.now());
            cupom.setNome("Cupom troca");
            cupom.setValor(itensPedido.getValorTotal());
            cupom.setCliente(itensPedido.getVenda().getCliente());
            cupom.setItensPedido(itensPedido);
            cupom.setTipoCupom(TipoCupom.TROCA);
            itensPedido.setCupomGerado(cupom);

            livro.setId(itensPedido.getIdLivro());
            livro = (Livro) this.livroDao.consultarPeloID(livro).get(0);
            livro.setEstoque(livro.getEstoque() + itensPedido.getQtdComprada());
            this.livroDao.alterarEstoqueLivro(livro.getId(), livro.getEstoque());

            return "";
        }
        return "";
    }
}
