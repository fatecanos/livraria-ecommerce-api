package com.fatec.livrariaecommerce.dao;

import com.fatec.livrariaecommerce.models.domain.ClienteEndereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteEnderecoDao extends JpaRepository<ClienteEndereco, Integer> {
}
