package com.fatec.livrariaecommerce.dao;

import com.fatec.livrariaecommerce.models.domain.Cliente;
import com.fatec.livrariaecommerce.models.domain.Cupom;
import com.fatec.livrariaecommerce.models.domain.EntidadeDominio;
import com.fatec.livrariaecommerce.models.domain.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public interface CupomDao extends JpaRepository<Cupom, Integer>, IDAO {

    @Override
    default EntidadeDominio salvar(EntidadeDominio entidadeDominio) {
        return this.saveAndFlush((Cupom) entidadeDominio);
    }

    @Override
    default EntidadeDominio alterar(EntidadeDominio entidadeDominio) {
        return this.saveAndFlush((Cupom) entidadeDominio);
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
            "   (?#{[0].id} IS NOT NULL AND obj.id = ?#{[0].id}) " +
            "   OR (?#{[0].ativo} IS NOT NULL AND obj.ativo = ?#{[0].ativo}) " +
            "ORDER BY " +
            "   obj.id" +
            "")
    List<EntidadeDominio> consultarPorIdOuAtivo(EntidadeDominio entidadeDominio);

    @Query("SELECT " +
            "   obj " +
            "FROM " +
            "   #{#entityName} obj " +
            "WHERE " +
            " obj.id NOT IN :ids " +
            "AND obj.cliente = :cliente " +
            "ORDER BY " +
            "   obj.id" +
            "")
    List<EntidadeDominio> consultarExcluindoIds(@Param("ids") List<Integer> ids, @Param("cliente") Cliente cliente);

    @Query("SELECT " +
            "   obj " +
            "FROM " +
            "   #{#entityName} obj " +
            "WHERE " +
            "   ?#{[0].cliente} IS NOT NULL AND obj.cliente = ?#{[0].cliente} " +
            "")
    List<EntidadeDominio> consultarPorCliente(@Param("dominio") EntidadeDominio entidadeDominio);

    @Override
    default List<EntidadeDominio> consultar(EntidadeDominio entidadeDominio) {
        if (entidadeDominio.getId() != null || entidadeDominio.getAtivo() != null) {
            return consultarPorIdOuAtivo(entidadeDominio);
        } else if (entidadeDominio.getId() == null && entidadeDominio.getAtivo() == null
                && ((Cupom) entidadeDominio).getCliente() != null
                && ((Cupom) entidadeDominio).getNome() != null
                && !((Cupom) entidadeDominio).getNome().trim().isEmpty()) {

            List<Integer> ids = Arrays.stream(((Cupom) entidadeDominio).getNome().
                    split(",")).map(cp -> Integer.valueOf(cp)).collect(Collectors.toList());
            return consultarExcluindoIds(ids, ((Cupom) entidadeDominio).getCliente());

        } else if (entidadeDominio.getId() == null && ((Cupom) entidadeDominio).getCliente() != null) {
            return consultarPorCliente(entidadeDominio);
        } else {
            return consultarPorIdOuAtivo(entidadeDominio);
        }
    }


}
