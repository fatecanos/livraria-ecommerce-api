package com.fatec.livrariaecommerce.dao;

import com.fatec.livrariaecommerce.models.domain.EntidadeDominio;
import com.fatec.livrariaecommerce.models.domain.GrupoPrecificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public interface GrupoPrecificacaoDao extends JpaRepository<GrupoPrecificacao, Integer>, IDAO {

    @Override
    default EntidadeDominio salvar(EntidadeDominio entidadeDominio) {
        return this.saveAndFlush((GrupoPrecificacao) entidadeDominio);
    }

    @Override
    default EntidadeDominio alterar(EntidadeDominio entidadeDominio) {
        return this.saveAndFlush((GrupoPrecificacao) entidadeDominio);
    }

    @Override
    @Transactional
    @Modifying
    @Query("UPDATE " +
            "  #{#entityName} obj " +
            "SET " +
            "   obj.ativo = false " +
            "WHERE " +
            "   obj.id = ?#{[0].id}")
    void excluir(@Param("dominio") EntidadeDominio entidadeDominio);

    @Override
    @Query("SELECT " +
            "   obj " +
            "FROM " +
            "   #{#entityName} obj " +
            "WHERE " +
            "   (?#{[0].id} IS NOT NULL AND obj.id = ?#{[0].id}) " +
            "   OR (?#{[0].ativo} IS NOT NULL AND obj.ativo = ?#{[0].ativo}) " +
            "ORDER BY " +
            "   obj.id" +
            "")
    List<EntidadeDominio> consultar(EntidadeDominio entidadeDominio);

}
