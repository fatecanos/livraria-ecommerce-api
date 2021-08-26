package com.fatec.livrariaecommerce.dao;

import com.fatec.livrariaecommerce.models.domain.Cliente;
import com.fatec.livrariaecommerce.models.domain.EntidadeDominio;
import com.fatec.livrariaecommerce.models.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@Service
public interface ClienteDao
        extends JpaRepository<Cliente, Integer>, IDAO {

    @Override
    default EntidadeDominio salvar(EntidadeDominio entidadeDominio) {
        return this.saveAndFlush((Cliente) entidadeDominio);
    }

    @Override
    default EntidadeDominio alterar(EntidadeDominio entidadeDominio) {
        return this.saveAndFlush((Cliente) entidadeDominio);
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
            "   OR (?#{[0].nome} IS NOT NULL AND (UPPER(obj.nome) LIKE(CONCAT('%', UPPER(?#{[0].nome}),'%')))) " +
            "   OR (?#{[0].cpf} IS NOT NULL AND (UPPER(obj.cpf) LIKE(CONCAT('%', UPPER(?#{[0].cpf}),'%')))) " +
            "   OR (?#{[0].sobrenome} IS NOT NULL AND (UPPER(obj.sobrenome) LIKE(CONCAT('%', UPPER(?#{[0].sobrenome}),'%')))) " +
            "")
    List<EntidadeDominio> consultarTabela(@Param("dominio") EntidadeDominio entidadeDominio);


    @Query("SELECT " +
            "   obj " +
            "FROM " +
            "   #{#entityName} obj " +
            "WHERE " +
            "   obj.usuario.id = :usuarioId" +
            "")
    Cliente consultarUsuarioId(@Param("usuarioId") Integer usuarioId);

    @Override
    default List<EntidadeDominio> consultar(EntidadeDominio entidadeDominio) {

        Cliente cliente = (Cliente) entidadeDominio;

        if (cliente.getId() != null) {
            return Collections.singletonList(this.getOne(entidadeDominio.getId()));
        } else if (cliente.getNome() != null || cliente.getCpf() != null || cliente.getSobrenome() != null) {
            return this.consultarTabela(cliente);
        } else if (cliente.getUsuario() != null && cliente.getUsuario().getId() != null) {
            return Collections.singletonList(this.consultarUsuarioId(cliente.getUsuario().getId()));
        } else {
            return this.consultarTabela(cliente);
        }
    }

}
