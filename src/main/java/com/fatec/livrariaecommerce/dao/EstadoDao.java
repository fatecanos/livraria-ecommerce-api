package com.fatec.livrariaecommerce.dao;

import com.fatec.livrariaecommerce.models.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
@Service
public interface EstadoDao
        extends JpaRepository<Cliente, Integer> {
}
