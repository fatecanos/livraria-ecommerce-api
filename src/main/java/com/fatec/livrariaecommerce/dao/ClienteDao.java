package com.fatec.livrariaecommerce.dao;

import com.fatec.livrariaecommerce.models.domain.Cliente;
import com.fatec.livrariaecommerce.models.domain.EntidadeDominio;
import com.fatec.livrariaecommerce.models.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
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
            "   OR (?#{[0].ativo} IS NOT NULL AND obj.ativo = TRUE) " +
            "   OR (?#{[0].nome} IS NOT NULL AND obj.nome = ?#{[0].nome}) " +
            "   OR (?#{[0].sobrenome} IS NOT NULL AND obj.sobrenome = ?#{[0].sobrenome}) " +
//            "   OR (?#{[0].usuario} IS NOT NULL AND ( " +
//            "           (?#{[0].usuario.id} IS NOT NULL AND obj.usuario.id = ?#{[0].usuario.id}) " +
//            "           OR (?#{[0].usuario.email} IS NOT NULL AND obj.usuario.email = ?#{[0].usuario.email}) " +
//            "       ))" +
            "")
    List<EntidadeDominio> consultar(@Param("dominio") EntidadeDominio entidadeDominio);
}
