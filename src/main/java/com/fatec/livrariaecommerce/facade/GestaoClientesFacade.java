package com.fatec.livrariaecommerce.facade;

import com.fatec.livrariaecommerce.dao.ClienteDao;
import com.fatec.livrariaecommerce.dao.DocumentoDao;
import com.fatec.livrariaecommerce.dao.TipoClienteDao;
import com.fatec.livrariaecommerce.dao.UsuarioDao;
import com.fatec.livrariaecommerce.models.domain.Cliente;
import com.fatec.livrariaecommerce.models.domain.Documento;
import com.fatec.livrariaecommerce.models.domain.TipoCliente;
import com.fatec.livrariaecommerce.models.domain.Usuario;
import com.fatec.livrariaecommerce.models.utils.CpfValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GestaoClientesFacade {
    final ClienteDao clienteDao;
    final TipoClienteDao tipoClienteDao;
    final UsuarioDao usuarioDao;
    final DocumentoDao documentoDao;

    @Autowired
    public GestaoClientesFacade(
            DocumentoDao documentoDao,
            ClienteDao clienteDao,
            TipoClienteDao tipoClienteDao,
            UsuarioDao usuarioDao) {
        this.clienteDao = clienteDao;
        this.tipoClienteDao = tipoClienteDao;
        this.usuarioDao = usuarioDao;
        this.documentoDao = documentoDao;
    }

    public Cliente save(Cliente cliente) {
        Optional<TipoCliente> tipoClienteOptional =
                tipoClienteDao.findTipoClienteByName("NOVO");

        Optional<Documento> isDocumentAlreadyPresent =
                this.documentoDao.isCpfAlreadyExists(
                        cliente.getDocumentos().stream()
                            .findFirst()
                            .get()
                            .getCodigo()
                );

        Optional<Usuario> isEmailAlreadyExist =
                this.usuarioDao.isEmailAlreadyPresent(cliente
                        .getUsuario().getEmail());

        if(tipoClienteOptional.isEmpty()) {
            TipoCliente novoTipoCliente = new TipoCliente(
                    1,
                    "NOVO",
                    "iniciante, primeira compra");
            tipoClienteDao.save(novoTipoCliente);
        }

        if(isDocumentAlreadyPresent.isPresent()) {
            throw new IllegalStateException("CPF já esta sendo " +
                    "usado por outro usuário");
        }

        if(isEmailAlreadyExist.isPresent()) {
            throw new IllegalStateException("E-mail já está sendo " +
                    "usado por um usuário no sistema");
        }

        String cpfTemp = cliente.getDocumentos()
                .stream().findFirst().get().getCodigo();

        if(!CpfValidator.isCPF(cpfTemp)) {
           throw new IllegalStateException("CPF inválido");
        }

        cliente.setTimeStamp(LocalDate.now());
        cliente.setAtivo(true);

        //TODO: rever esta regra de contro de tipo cliente
        cliente.setTipoCliente(this.tipoClienteDao.getOne(1));
        //TODO: implementar regra de obtenção de tipo documento

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


        /*
        if(!clienteTemp.equals(cliente)) {
            Optional<Usuario> isEmailAlreadyExist =
                    this.usuarioDao.isEmailAlreadyPresent(cliente
                            .getUsuario().getEmail());
            if (isEmailAlreadyExist.isPresent()) {
                throw new IllegalStateException("E-mail já está sendo " +
                        "usado por um usuário no sistema");
            }
        }
        */

        if(!clienteTemp.isAtivo()) {
            throw new IllegalStateException("Usuário não está ativo");
        }

        String cpfTemp = cliente.getDocumentos()
                .stream().findFirst().get().getCodigo();

        if(!CpfValidator.isCPF(cpfTemp)) {
            throw new IllegalStateException("CPF inválido");
        }

        clienteTemp.setId(id);
        clienteTemp.setAtivo(true);
        clienteTemp.setTimeStamp(LocalDate.now());
        clienteTemp.setTipoCliente(cliente.getTipoCliente());
        clienteTemp.setEnderecos(cliente.getEnderecos());
        clienteTemp.setDocumentos(cliente.getDocumentos());
        clienteTemp.setDataNascimento(cliente.getDataNascimento());
        clienteTemp.setNome(cliente.getNome());
        clienteTemp.setSobrenome(cliente.getSobrenome());
        clienteTemp.setUsuario(cliente.getUsuario());

        return this.clienteDao.save(clienteTemp);
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
