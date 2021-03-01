package com.fatec.livrariaecommerce.dao;

import com.fatec.livrariaecommerce.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
@Service
public interface EnderecoDao
        extends JpaRepository<Endereco, Integer> {
}
