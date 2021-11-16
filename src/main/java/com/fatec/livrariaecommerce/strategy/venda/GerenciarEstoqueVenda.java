package com.fatec.livrariaecommerce.strategy.venda;

import com.fatec.livrariaecommerce.dao.LivroDao;
import com.fatec.livrariaecommerce.models.domain.EntidadeDominio;
import com.fatec.livrariaecommerce.models.domain.ItensPedido;
import com.fatec.livrariaecommerce.models.domain.Livro;
import com.fatec.livrariaecommerce.models.domain.Venda;
import com.fatec.livrariaecommerce.strategy.IStrategy;

public class GerenciarEstoqueVenda implements IStrategy {

    public final LivroDao livroDao;

    public GerenciarEstoqueVenda(LivroDao livroDao) {
        this.livroDao = livroDao;
    }

    @Override
    public String processar(EntidadeDominio dominio) {
        Venda venda = (Venda) dominio;
        for (ItensPedido it : venda.getItensPedidos()) {
            Livro livro = new Livro();
            livro.setId(it.getIdLivro());
            livro = (Livro) this.livroDao.consultarPeloID(livro).get(0);
            livro.setEstoque(livro.getEstoque() - it.getQtdComprada());
            this.livroDao.reduzirEstoqueLivro(livro.getId(), livro.getEstoque());
        }
        return "";
    }
}
