package com.fatec.livrariaecommerce.negocio.venda;

import com.fatec.livrariaecommerce.dao.VendaDao;
import com.fatec.livrariaecommerce.facade.Facade;
import com.fatec.livrariaecommerce.models.domain.Cupom;
import com.fatec.livrariaecommerce.models.domain.EntidadeDominio;
import com.fatec.livrariaecommerce.models.domain.Venda;
import com.fatec.livrariaecommerce.negocio.IStrategy;

public class ValidaCupom implements IStrategy {

    private final VendaDao vendaDao;

    public ValidaCupom(VendaDao vendaDao) {
        this.vendaDao = vendaDao;
    }

    @Override
    public String processar(EntidadeDominio dominio) {
        Venda venda = (Venda) dominio;
        for(Cupom cupom : venda.getCupoms()){
            if(!this.vendaDao.consultarCupom(cupom.getId()).isEmpty()){
                return "Cupom já foi utilizado: " + cupom.getId();
            }
        }
        return "";
    }
}
