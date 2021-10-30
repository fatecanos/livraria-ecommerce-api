package com.fatec.livrariaecommerce.strategy.cupom;

import com.fatec.livrariaecommerce.dao.CupomDao;
import com.fatec.livrariaecommerce.models.domain.Cupom;
import com.fatec.livrariaecommerce.models.domain.EntidadeDominio;
import com.fatec.livrariaecommerce.models.domain.Resultado;
import com.fatec.livrariaecommerce.strategy.IStrategy;

import java.util.Random;

public class TipoCupom implements IStrategy {

    //TODO: CONSULTAR CODIGOS PARA GERAR UM NOVO E SALVAR

    public final CupomDao cupomDao;

    public TipoCupom(CupomDao cupomDao) {
        this.cupomDao = cupomDao;
    }

    @Override
    public String processar(EntidadeDominio dominio) {
        Cupom cupom = (Cupom) dominio;
        if (cupom.getTipoCupom() == com.fatec.livrariaecommerce.models.domain.TipoCupom.PROMOCIONAL) {
            if (cupom.getCodigo() == null || cupom.getCodigo() == "") {
                String codigo = String.format("%07d", new Random().nextInt(1000000));
                cupom.setCodigo(codigo);
                if (!this.cupomDao.consultarPorCodigoPromocional(cupom).isEmpty()) {
                    String newCodigo = String.format("%07d", new Random().nextInt(1000000));
                    cupom.setCodigo(newCodigo);
                }
            }
        }

        return "";

    }
}
