package com.fatec.livrariaecommerce.command;

import com.fatec.livrariaecommerce.models.domain.EntidadeDominio;
import com.fatec.livrariaecommerce.negocio.IStrategy;

import java.util.List;

public class ExecutarRegras {

    public static StringBuilder executarRegras(EntidadeDominio dominio, List<IStrategy> rnsEntidade) {
        StringBuilder sb = new StringBuilder();

        for (IStrategy rn : rnsEntidade) {
            String msg = rn.processar(dominio);
            if (msg != null) {
                sb.append(msg);
            }
        }
        return sb;
    }
}
