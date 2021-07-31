package com.fatec.livrariaecommerce.dao;

import com.fatec.livrariaecommerce.models.domain.Cliente;
import com.fatec.livrariaecommerce.models.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioDao
        extends JpaRepository<Cliente, Integer> {
//    @Query("SELECT s FROM Usuario s WHERE s.email = ?1")
//    Optional<Usuario> isEmailAlreadyPresent(String email);


    @Query("SELECT s FROM Usuario s WHERE s.email = :email AND s.senha = :senha")
    Optional<Usuario> findByEmailAndSenha(@Param("email") String email,
                                                @Param("senha") String senha);


}
