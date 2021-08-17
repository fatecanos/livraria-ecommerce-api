package com.fatec.livrariaecommerce.dao;

import com.fatec.livrariaecommerce.models.domain.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface CidadeDao extends JpaRepository<Cidade, Integer> {

    Optional<Cidade> findByNome(String name);

    @Query("SELECT c FROM Cidade c WHERE c.estado.id = :estado")
    Optional<List<Cidade>> findAllById(@Param("estado") int estado);
}
