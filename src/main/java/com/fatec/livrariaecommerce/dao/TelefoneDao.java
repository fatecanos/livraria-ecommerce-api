package com.fatec.livrariaecommerce.dao;

import com.fatec.livrariaecommerce.models.domain.Endereco;
import com.fatec.livrariaecommerce.models.domain.EntidadeDominio;
import com.fatec.livrariaecommerce.models.domain.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface TelefoneDao extends JpaRepository<Telefone, Integer>, IDAO {

    @Override
    default EntidadeDominio salvar(EntidadeDominio entidadeDominio) {
        return this.saveAndFlush((Telefone) entidadeDominio);
    }

    @Override
    default EntidadeDominio alterar(EntidadeDominio entidadeDominio) {
        return this.saveAndFlush((Telefone) entidadeDominio);
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
            "   OR (?#{[0].telefone} IS NOT NULL AND obj.telefone = ?#{[0].telefone}) " +
            "   OR (?#{[0].cliente} IS NOT NULL AND ( " +
            "           (?#{[0].cliente.id} IS NOT NULL AND obj.cliente.id = ?#{[0].cliente.id}) " +
            "       )) " +
            "ORDER BY " +
            "   obj.id" +
            "")
    List<EntidadeDominio> consultar(EntidadeDominio entidadeDominio);

}
