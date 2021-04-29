package com.fatec.livrariaecommerce.dao;

import com.fatec.livrariaecommerce.models.domain.Cliente;
import com.fatec.livrariaecommerce.models.domain.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DocumentoDao
        extends JpaRepository<Cliente, Integer> {
    @Query("select d from Documento d where d.codigo=?1")
    Optional<Documento> isCpfAlreadyExists(String cpf);
}
