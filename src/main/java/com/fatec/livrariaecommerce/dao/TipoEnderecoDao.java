package com.fatec.livrariaecommerce.dao;

import com.fatec.livrariaecommerce.domain.Endereco;
import com.fatec.livrariaecommerce.domain.TipoEndereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
@Service
public interface TipoEnderecoDao
        extends JpaRepository<TipoEndereco, Integer> {
}
