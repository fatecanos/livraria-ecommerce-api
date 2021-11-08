package com.fatec.livrariaecommerce.dao;

import com.fatec.livrariaecommerce.models.domain.Cliente;
import com.fatec.livrariaecommerce.models.domain.EntidadeDominio;
import com.fatec.livrariaecommerce.models.domain.Telefone;
import com.fatec.livrariaecommerce.models.domain.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

public interface VendaDao extends JpaRepository<Venda, Integer>, IDAO {

    @Override
    default EntidadeDominio salvar(EntidadeDominio entidadeDominio) {
        return this.saveAndFlush((Venda) entidadeDominio);
    }

    @Override
    default EntidadeDominio alterar(EntidadeDominio entidadeDominio) {
        return this.saveAndFlush((Venda) entidadeDominio);
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
            "   OR (?#{[0].cliente} IS NOT NULL AND obj.cliente = ?#{[0].cliente}) " +
            "   OR (?#{[0].statusVenda} IS NOT NULL AND obj.statusVenda = ?#{[0].statusVenda}) " +
            "")
    List<EntidadeDominio> consultarTabela(EntidadeDominio entidadeDominio);

    @Query("SELECT " +
            "   obj " +
            "FROM " +
            "   #{#entityName} obj " +
            "WHERE " +
            "   ?#{[0].id} IS NOT NULL AND obj.id = ?#{[0].id} " +
            "")
    List<EntidadeDominio> consultarSomenteID(EntidadeDominio entidadeDominio);


    @Query("SELECT " +
            "   obj " +
            "FROM " +
            "Venda obj " +
            "INNER JOIN obj.cupoms cp ON cp.id = :idCupom")
    List<EntidadeDominio> consultarCupom(@Param("idCupom") int idCupom);

    @Query("SELECT " +
            "   obj " +
            "FROM " +
            "Venda obj " +
            "WHERE obj.cupoms.size > 0")
    List<EntidadeDominio> consultarComCupom();

    @Query("SELECT " +
            "   obj " +
            "FROM " +
            "   #{#entityName} obj " +

            "WHERE " +

            "   obj.dataCriacao BETWEEN :inicio AND :fim" +

            "")
    List<EntidadeDominio> consultarPeriodo(@Param("inicio") LocalDateTime inicio, @Param("fim") LocalDateTime fim);

    @Query("SELECT " +
            "   obj " +
            "FROM " +
            "   #{#entityName} obj " +
            "WHERE " +
            "   obj.cliente.genero = ?#{[0].cliente.genero} " +
            "")
    List<EntidadeDominio> consultarGeneroCliente(@Param("dominio") EntidadeDominio entidadeDominio);

    @Query("SELECT " +
            "   obj " +
            "FROM " +
            "   #{#entityName} obj " +
            "WHERE " +
            "   (?#{[0].cliente} IS NOT NULL AND obj.cliente = ?#{[0].cliente}) " +
            "")
    List<EntidadeDominio> consultarPedidosCliente(@Param("dominio") EntidadeDominio entidadeDominio);


    @Override
    default List<EntidadeDominio> consultar(EntidadeDominio entidadeDominio) {

        if (entidadeDominio.getId() != null) {

            return consultarTabela(entidadeDominio);
        } else if (entidadeDominio.getId() == null && entidadeDominio.getDataCriacao() != null) {
            LocalDateTime inicio = entidadeDominio.getDataCriacao()
                    .with(TemporalAdjusters.firstDayOfMonth())
                    .withHour(0)
                    .withMinute(0);
            LocalDateTime fim = entidadeDominio.getDataCriacao()
                    .with(TemporalAdjusters.lastDayOfMonth())
                    .withHour(23)
                    .withMinute(59);
            return consultarPeriodo(inicio, fim);
        } else if (((Venda) entidadeDominio).getCliente() != null && entidadeDominio.getAtivo() != null) {
            return consultarPedidosCliente(entidadeDominio);
        } else if (((Venda) entidadeDominio).getCliente() != null) {
            return consultarGeneroCliente(entidadeDominio);
        } else if (entidadeDominio.getAtivo() != null) {
            return consultarTabela(entidadeDominio);
        }

        return null;
    }

}
