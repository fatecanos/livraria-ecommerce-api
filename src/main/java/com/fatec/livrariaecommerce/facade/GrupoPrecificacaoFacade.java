package com.fatec.livrariaecommerce.facade;

import com.fatec.livrariaecommerce.dao.GrupoPrecificacaoDao;
import com.fatec.livrariaecommerce.models.domain.GrupoPrecificacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrupoPrecificacaoFacade {

    private final GrupoPrecificacaoDao dao;

    @Autowired
    public GrupoPrecificacaoFacade(GrupoPrecificacaoDao dao) {
        this.dao = dao;
    }

    public List<GrupoPrecificacao> obterGrupos() {
        return this.dao.findAll();
    }


}
