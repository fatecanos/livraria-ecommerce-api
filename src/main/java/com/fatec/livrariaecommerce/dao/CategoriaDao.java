package com.fatec.livrariaecommerce.dao;

import com.fatec.livrariaecommerce.models.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface CategoriaDao extends JpaRepository<Categoria, Integer> {

}
