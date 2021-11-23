package com.fatec.livrariaecommerce.strategy.livro;

import com.fatec.livrariaecommerce.models.domain.EntidadeDominio;
import com.fatec.livrariaecommerce.models.domain.Livro;
import com.fatec.livrariaecommerce.strategy.IStrategy;

public class ValidaEntradaEstoque implements IStrategy {
    @Override
    public String processar(EntidadeDominio dominio) {
        Livro livro = (Livro) dominio;
        if(livro.getEstoque() == 0){
            return "A entrada de estoque deste produto deve ser maior que 0. Tente novamente.";
        }
        return "";
    }
}
