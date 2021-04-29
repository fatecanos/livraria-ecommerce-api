package com.fatec.livrariaecommerce.dao;

import com.fatec.livrariaecommerce.models.domain.Dimensoes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DimensaoDao extends JpaRepository<Dimensoes, Integer> {
}
