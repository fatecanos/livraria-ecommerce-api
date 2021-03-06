package com.fatec.livrariaecommerce.dao;

import com.fatec.livrariaecommerce.models.domain.Dimensoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository @Service
public interface DimensaoDao extends JpaRepository<Dimensoes, Integer> {
}
