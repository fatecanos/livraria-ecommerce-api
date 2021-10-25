package com.fatec.livrariaecommerce.strategy.cupom;

import com.fatec.livrariaecommerce.dao.VendaDao;
import com.fatec.livrariaecommerce.models.domain.Cupom;
import com.fatec.livrariaecommerce.models.domain.EntidadeDominio;
import com.fatec.livrariaecommerce.models.domain.Venda;
import com.fatec.livrariaecommerce.strategy.IStrategy;

import java.util.List;

public class ValidaCupomUsados implements IStrategy {

    public final VendaDao vendaDao;

    public ValidaCupomUsados(VendaDao vendaDao) {
        this.vendaDao = vendaDao;
    }

    @Override
    public String processar(EntidadeDominio dominio) {

        String idCupomList = "";

        List<EntidadeDominio> vendaList = this.vendaDao.consultarComCupom();
        if (!vendaList.isEmpty()) {

            for (EntidadeDominio venda : vendaList) {
                for (Cupom cupom : ((Venda) venda).getCupoms()) {
                    idCupomList += cupom.getId() + ",";
                }
            }
            ((Cupom) dominio).setNome(idCupomList);
        }

        return "";
    }
}
