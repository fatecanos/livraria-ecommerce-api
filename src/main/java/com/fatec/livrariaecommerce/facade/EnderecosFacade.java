package com.fatec.livrariaecommerce.facade;

import com.fatec.livrariaecommerce.dao.EnderecoDao;
import com.fatec.livrariaecommerce.dao.EstadoDao;
import com.fatec.livrariaecommerce.dao.TipoEnderecoDao;
import com.fatec.livrariaecommerce.domain.Endereco;
import com.fatec.livrariaecommerce.domain.Estado;
import com.fatec.livrariaecommerce.domain.TipoEndereco;
import com.fatec.livrariaecommerce.dto.EnderecoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecosFacade {

    final EnderecoDao enderecoDao;
    final TipoEnderecoDao tipoEnderecoDao;
    final EstadoDao estadoDao;

    @Autowired
    public EnderecosFacade(
            EstadoDao estadoDao,
            TipoEnderecoDao tipoEnderecoDao,
            EnderecoDao enderecoDao) {
        this.enderecoDao = enderecoDao;
        this.tipoEnderecoDao = tipoEnderecoDao;
        this.estadoDao = estadoDao;
    }

    public List<TipoEndereco> getTipoEnderecos() {
        return this.tipoEnderecoDao.findAll();
    }

    public List<Estado> getEstados() {
        return null;
    }

    public List<Endereco> getEnderecos() {
        return null;
    }

    public void saveEndereco(int idCliente, EnderecoDTO endereco) {

    }

}
