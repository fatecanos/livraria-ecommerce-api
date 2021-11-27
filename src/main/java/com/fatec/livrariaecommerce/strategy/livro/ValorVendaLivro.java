package com.fatec.livrariaecommerce.strategy.livro;

import com.fatec.livrariaecommerce.dao.GrupoPrecificacaoDao;
import com.fatec.livrariaecommerce.models.domain.EntidadeDominio;
import com.fatec.livrariaecommerce.models.domain.GrupoPrecificacao;
import com.fatec.livrariaecommerce.models.domain.Livro;
import com.fatec.livrariaecommerce.strategy.IStrategy;

public class ValorVendaLivro implements IStrategy {

    public final GrupoPrecificacaoDao grupoPrecificacaoDao;

    public ValorVendaLivro(GrupoPrecificacaoDao grupoPrecificacaoDao) {
        this.grupoPrecificacaoDao = grupoPrecificacaoDao;
    }

    @Override
    public String processar(EntidadeDominio dominio) {
        Livro livro = (Livro) dominio;
        double valorTotal = 0;
        GrupoPrecificacao grupoPrecificacao
                = (GrupoPrecificacao) this.grupoPrecificacaoDao.consultar(livro.getGrupoPrecificacao()).get(0);
        valorTotal = livro.getValorCompra() * (grupoPrecificacao.getMargem() / 100);
        livro.setValorVenda(valorTotal + livro.getValorCompra());
        return "";
    }
}
