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

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@Service
public interface UsuarioDao
        extends JpaRepository<Usuario, Integer>, IDAO {

    @Override
    default EntidadeDominio salvar(EntidadeDominio entidadeDominio) {
        return this.saveAndFlush((Usuario) entidadeDominio);
    }

    @Override
    default EntidadeDominio alterar(EntidadeDominio entidadeDominio) {
        return this.saveAndFlush((Usuario) entidadeDominio);
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
            "   OR (?#{[0].email} IS NOT NULL AND obj.email = ?#{[0].email}) " +
            "   AND (?#{[0].senha} IS NOT NULL AND obj.senha = ?#{[0].senha}) " +
            "   OR (?#{[0].perfilUsuario} IS NOT NULL AND obj.perfilUsuario = ?#{[0].perfilUsuario}) " +
            "")
    List<EntidadeDominio> consultarTabela(EntidadeDominio entidadeDominio);

    @Query("SELECT " +
            "   obj " +
            "FROM " +
            "   #{#entityName} obj " +
            "WHERE " +
            "   obj.email = :email" +
            "")
    List<EntidadeDominio> consultarUsuarioEmail(@Param("email") String email);

    @Query("SELECT " +
            "   obj " +
            "FROM " +
            "   #{#entityName} obj " +
            "WHERE " +
            "   obj.email = :email " +
            "   AND obj.senha = :senha " +
            "")
    List<EntidadeDominio> loginEmailSenha(@Param("email") String email, @Param("senha") String senha);


    @Override
    default List<EntidadeDominio> consultar(EntidadeDominio entidadeDominio) {
        Usuario usuario = (Usuario) entidadeDominio;

        if (usuario.getId() != null) {
            return Collections.singletonList(this.getOne(entidadeDominio.getId()));
        } else if (usuario.getEmail() != null && usuario.getSenha() == null) {
            return this.consultarUsuarioEmail(usuario.getEmail());
        } else if (usuario.getEmail() != null && usuario.getSenha() != null) {
            return this.loginEmailSenha(usuario.getEmail(), usuario.getSenha());
        } else {
            return this.consultarTabela(usuario);
        }
    }
}
