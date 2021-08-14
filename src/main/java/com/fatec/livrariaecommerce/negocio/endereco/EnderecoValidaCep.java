package com.fatec.livrariaecommerce.negocio.endereco;

import com.fatec.livrariaecommerce.models.domain.Endereco;
import com.fatec.livrariaecommerce.models.domain.EntidadeDominio;
import com.fatec.livrariaecommerce.negocio.IStrategy;

public class EnderecoValidaCep implements IStrategy {
    @Override
    public String processar(EntidadeDominio dominio) {
        Endereco endereco = (Endereco) dominio;

        if (endereco.getCep().length() == 8)
            return "";

        return "CEP invalido.\n";
    }
}
