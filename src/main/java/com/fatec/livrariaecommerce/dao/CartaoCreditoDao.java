package com.fatec.livrariaecommerce.dao;

import com.fatec.livrariaecommerce.models.domain.CartaoCredito;
import com.fatec.livrariaecommerce.models.domain.Cidade;
import com.fatec.livrariaecommerce.models.domain.EntidadeDominio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartaoCreditoDao extends JpaRepository<CartaoCredito, Integer>, IDAO {

    @Override
    default EntidadeDominio salvar(EntidadeDominio entidadeDominio) {
        return this.saveAndFlush((CartaoCredito) entidadeDominio);
    }

    @Override
    default EntidadeDominio alterar(EntidadeDominio entidadeDominio) {
        return this.saveAndFlush((CartaoCredito) entidadeDominio);
    }

    @Override
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
            "   OR (?#{[0].bandeira} IS NOT NULL AND obj.bandeira = ?#{[0].bandeira}) " +
            "   OR (?#{[0].isPreferencial} IS NOT NULL AND obj.isPreferencial = ?#{[0].isPreferencial}) " +
            "   OR (?#{[0].codigoSeguranca} IS NOT NULL AND obj.codigoSeguranca = ?#{[0].codigoSeguranca}) " +
            "   OR (?#{[0].nomeImpressoCartao} IS NOT NULL AND obj.nomeImpressoCartao = ?#{[0].nomeImpressoCartao}) " +
            "   OR (?#{[0].numeroCartao} IS NOT NULL AND obj.numeroCartao = ?#{[0].numeroCartao}) " +
            "")
    List<EntidadeDominio> consultar(@Param("dominio") EntidadeDominio entidadeDominio);

}
