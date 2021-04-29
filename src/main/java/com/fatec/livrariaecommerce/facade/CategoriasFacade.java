package com.fatec.livrariaecommerce.facade;

import com.fatec.livrariaecommerce.dao.CategoriaDao;
import com.fatec.livrariaecommerce.models.dto.CategoriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriasFacade {
    final CategoriaDao categoriaDao;

    @Autowired
    public CategoriasFacade(CategoriaDao categoriaDao) {
        this.categoriaDao = categoriaDao;
    }

    public List<CategoriaDTO> listar() throws Exception {
        List<CategoriaDTO> lista = this.categoriaDao.findAll().stream().map(categoria -> {
            CategoriaDTO dto = new CategoriaDTO(
                    categoria.getId(),
                    categoria.getDescricao()
            );
            return dto;
        }).collect(Collectors.toList());
        System.out.println(lista);
        return lista;
    }
}
