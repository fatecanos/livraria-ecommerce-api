package com.fatec.livrariaecommerce.dao;

import com.fatec.livrariaecommerce.domain.TipoCliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoClienteDao
        extends JpaRepository<TipoCliente, Integer> {
}
