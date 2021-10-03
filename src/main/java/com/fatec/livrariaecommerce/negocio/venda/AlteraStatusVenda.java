package com.fatec.livrariaecommerce.negocio.venda;

import com.fatec.livrariaecommerce.models.domain.EntidadeDominio;
import com.fatec.livrariaecommerce.models.domain.Venda;
import com.fatec.livrariaecommerce.negocio.IStrategy;

public class AlteraStatusVenda implements IStrategy {
    @Override
    public String processar(EntidadeDominio dominio) {
        Venda venda = (Venda) dominio;




        return null;
    }
}
