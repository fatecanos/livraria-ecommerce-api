package com.fatec.livrariaecommerce.dao;

import com.fatec.livrariaecommerce.models.domain.EntidadeDominio;

import java.util.List;

public interface IDAO {

    // ***********************************************************************

    EntidadeDominio salvar(EntidadeDominio entidadeDominio);

    EntidadeDominio alterar(EntidadeDominio entidadeDominio);

    void excluir(EntidadeDominio entidadeDominio);

    List<EntidadeDominio> consultar(EntidadeDominio entidadeDominio);

    // ***********************************************************************

}
