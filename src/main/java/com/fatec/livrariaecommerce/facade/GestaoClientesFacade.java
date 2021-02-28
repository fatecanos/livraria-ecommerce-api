package com.fatec.livrariaecommerce.facade;

import com.fatec.livrariaecommerce.dao.ClienteDao;
import com.fatec.livrariaecommerce.dao.TipoClienteDao;
import com.fatec.livrariaecommerce.dao.UsuarioDao;
import com.fatec.livrariaecommerce.domain.Cliente;
import com.fatec.livrariaecommerce.domain.TipoCliente;
import com.fatec.livrariaecommerce.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GestaoClientesFacade {
    private final ClienteDao clienteDao;
    private final TipoClienteDao tipoClienteDao;
    private final UsuarioDao usuarioDao;

    @Autowired
    public GestaoClientesFacade(
            ClienteDao clienteDao,
            TipoClienteDao tipoClienteDao,
            UsuarioDao usuarioDao) {
        this.clienteDao = clienteDao;
        this.tipoClienteDao = tipoClienteDao;
        this.usuarioDao = usuarioDao;
    }

    public Cliente save(Cliente cliente) {
        Optional<TipoCliente> tipoClienteOptional =
                tipoClienteDao.findTipoClienteByName("NOVO");

        Optional<Usuario> isEmailPresent =
                usuarioDao.isEmailAlreadyPresent(
                        cliente.getUsuario().getEmail());

        if(tipoClienteOptional.isEmpty()) {
            TipoCliente novoTipoCliente = new TipoCliente(
                    1,
                    "NOVO",
                    "iniciante, primeira compra");
            tipoClienteDao.save(novoTipoCliente);
        }

        if(isEmailPresent.isPresent()) {
            throw new IllegalStateException();
        }

        cliente.setTimeStamp(LocalDate.now());
        cliente.setAtivo(true);
        cliente.setTipoCliente(this.tipoClienteDao.getOne(1));

        return this.clienteDao.save(cliente);
    }

    public List<Cliente> viewAll() {
        List<Cliente> clientes = this.clienteDao.findAll();

        return clientes.stream()
                .filter(cliente -> cliente.isAtivo())
                .collect(Collectors.toList());
    }

    public Cliente updateById(int id, Cliente cliente) {
        Cliente clienteTemp = this.clienteDao.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "identificador inválido: "+ id));

        if(!clienteTemp.isAtivo()) {
            throw new IllegalStateException();
        }

        cliente.setId(id);
        cliente.setAtivo(true);
        cliente.setTimeStamp(LocalDate.now());
        return this.clienteDao.save(cliente);
    }

    public Cliente disableById(int id) {
        Cliente clienteTemp = this.clienteDao.getOne(id);
        clienteTemp.setAtivo(false);
        return this.clienteDao.save(clienteTemp);
    }

    public Cliente findClienteById(int id) {
        return this.clienteDao.getOne(id);
    }
}
