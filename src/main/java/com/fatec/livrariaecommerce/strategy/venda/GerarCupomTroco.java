package com.fatec.livrariaecommerce.strategy.venda;

import com.fatec.livrariaecommerce.dao.CupomDao;
import com.fatec.livrariaecommerce.models.domain.*;
import com.fatec.livrariaecommerce.strategy.IStrategy;

import java.time.LocalDateTime;

public class GerarCupomTroco implements IStrategy {

    public final CupomDao cupomDao;

    public GerarCupomTroco(CupomDao cupomDao) {
        this.cupomDao = cupomDao;
    }

    @Override
    public String processar(EntidadeDominio dominio) {
        Venda venda = (Venda) dominio;
        if (venda.getTrocoCupom() != 0) {
            Cupom cupom = new Cupom();
            cupom.setAtivo(true);
            cupom.setDataCriacao(LocalDateTime.now());
            cupom.setNome("Cupom de troco do pedido #" + venda.getNumero());
            cupom.setValor(venda.getTrocoCupom());
            cupom.setCliente(venda.getCliente());
            cupom.setTipoCupom(TipoCupom.TROCA);
            this.cupomDao.salvar(cupom);
        }
        return "";
    }
}
