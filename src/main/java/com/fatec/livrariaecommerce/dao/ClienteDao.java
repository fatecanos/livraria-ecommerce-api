package com.fatec.livrariaecommerce.dao;

import com.fatec.livrariaecommerce.models.domain.Cliente;
import com.fatec.livrariaecommerce.models.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Repository
@Service
public interface ClienteDao
        extends JpaRepository<Cliente, Integer> {

    @Query("SELECT c FROM Cliente c WHERE c.usuario.id = :usuarioID")
    Optional<Cliente> findClienteByUsuarioID(@Param("usuarioID") int usuarioID);



}
