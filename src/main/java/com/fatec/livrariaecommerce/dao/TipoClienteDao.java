package com.fatec.livrariaecommerce.dao;

import com.fatec.livrariaecommerce.domain.TipoCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TipoClienteDao
        extends JpaRepository<TipoCliente, Integer> {

    @Query("SELECT s FROM TipoCliente s WHERE s.nome = ?1")
    Optional<TipoCliente> findTipoClienteByName(String name);
}
