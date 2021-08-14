package com.fatec.livrariaecommerce.facade;

import com.fatec.livrariaecommerce.models.domain.EntidadeDominio;
import com.fatec.livrariaecommerce.models.domain.Resultado;

public interface IFacade {
    public Resultado salvar(EntidadeDominio dominio);
    public Resultado alterar(EntidadeDominio dominio);
    public Resultado excluir(EntidadeDominio dominio);
    public Resultado consultar(EntidadeDominio dominio);
    public Resultado visualizar(EntidadeDominio dominio);

}
