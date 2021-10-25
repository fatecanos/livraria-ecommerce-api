package com.fatec.livrariaecommerce.strategy;

import com.fatec.livrariaecommerce.models.domain.EntidadeDominio;

public interface IStrategy {
    String processar(EntidadeDominio dominio);
}
