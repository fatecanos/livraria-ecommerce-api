package com.fatec.livrariaecommerce.dao;

import com.fatec.livrariaecommerce.models.domain.EntidadeDominio;
import com.fatec.livrariaecommerce.models.domain.Livro;
import com.fatec.livrariaecommerce.models.domain.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

public interface LivroDao extends JpaRepository<Livro, Integer>, IDAO {

    @Override
    default EntidadeDominio salvar(EntidadeDominio entidadeDominio) {
        return this.saveAndFlush((Livro) entidadeDominio);
    }

    @Override
    default EntidadeDominio alterar(EntidadeDominio entidadeDominio) {
        return this.saveAndFlush((Livro) entidadeDominio);
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


    @Query("SELECT " +
            "   obj " +
            "FROM " +
            "   #{#entityName} obj " +
            "WHERE " +
            "   (?#{[0].ativo} IS NOT NULL AND obj.ativo = TRUE) " +
            "   OR (?#{[0].autor} IS NOT NULL AND obj.autor = ?#{[0].autor}) " +
            "   OR (?#{[0].titulo} IS NOT NULL AND obj.titulo = ?#{[0].titulo}) " +
            "   OR (?#{[0].editora} IS NOT NULL AND obj.editora = ?#{[0].editora}) " +
            "")
    List<EntidadeDominio> consultarTabela(@Param("dominio") EntidadeDominio entidadeDominio);

    @Override
    default List<EntidadeDominio> consultar(EntidadeDominio entidadeDominio) {

        Livro livro = (Livro) entidadeDominio;
        if (livro.getId() != null) {
            return Collections.singletonList(this.getOne(entidadeDominio.getId()));
        } else {
            return this.consultarTabela(livro);
        }

    }


}
