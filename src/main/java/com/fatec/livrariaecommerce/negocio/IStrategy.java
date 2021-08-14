package com.fatec.livrariaecommerce.negocio;

import com.fatec.livrariaecommerce.models.domain.EntidadeDominio;

public interface IStrategy {
    String processar(EntidadeDominio dominio);
}
