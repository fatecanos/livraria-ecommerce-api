package com.fatec.livrariaecommerce.facade;

import com.fatec.livrariaecommerce.dao.ClienteDao;
import com.fatec.livrariaecommerce.dao.TipoClienteDao;
import com.fatec.livrariaecommerce.domain.Cliente;
import com.fatec.livrariaecommerce.domain.TipoCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class GestaoClientesFacade {
    private ClienteDao clienteDao;
    private TipoClienteDao tipoClienteDao;

    @Autowired
    public GestaoClientesFacade(
            ClienteDao clienteDao, TipoClienteDao tipoClienteDao) {
        this.clienteDao = clienteDao;
        this.tipoClienteDao = tipoClienteDao;
    }

    public Cliente save(Cliente cliente) {
        TipoCliente tipoCliente = this.tipoClienteDao.getOne(1);

        cliente.setTimeStamp(LocalDate.now());
        cliente.setAtivo(true);
        cliente.setTipoCliente(tipoCliente);

        return this.clienteDao.save(cliente);
    }

    public List<Cliente> viewAll() {
        return this.clienteDao.findAll();
    }

    public Cliente updateById(int id, Cliente cliente) {
        Cliente clienteTemp = this.clienteDao.getOne(id);

        if(clienteTemp.isAtivo()) {
            return this.clienteDao.save(cliente);
        }
        return null;
    }

    public void deleteById(int id) {
        this.clienteDao.deleteById(id);
    }
}
