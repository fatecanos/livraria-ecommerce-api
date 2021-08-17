package com.fatec.livrariaecommerce.facade;

import com.fatec.livrariaecommerce.dao.ClienteDao;
import com.fatec.livrariaecommerce.dao.DocumentoDao;
import com.fatec.livrariaecommerce.dao.TipoClienteDao;
import com.fatec.livrariaecommerce.dao.UsuarioDao;
import com.fatec.livrariaecommerce.models.domain.*;
import com.fatec.livrariaecommerce.models.utils.CpfValidator;
import com.fatec.livrariaecommerce.negocio.IStrategy;
import com.fatec.livrariaecommerce.negocio.endereco.EnderecoValidaCep;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GestaoClientesFacade implements IFacade {

    // ***********************************************************************

    private final ClienteDao clienteDao;
    private final TipoClienteDao tipoClienteDao;
    private final UsuarioDao usuarioDao;
    private final DocumentoDao documentoDao;

    private StringBuilder sb = new StringBuilder();
    Map<String, List<IStrategy>> regrasNegocio = new HashMap<>();

    // ***********************************************************************

    @PostConstruct
    public void postConstruct() {
        List<IStrategy> rnsSalvar = new ArrayList<>();
        // Instanciar classes de regras de negocio e adicionar na lista de rns
        rnsSalvar.add(new EnderecoValidaCep());

        regrasNegocio.put("SALVAR", rnsSalvar);

        List<IStrategy> rnsAlterar = new ArrayList<>();
        // Instanciar classes de regras de negocio e adicionar na lista de rns
        rnsAlterar.add(new EnderecoValidaCep());

        regrasNegocio.put("ALTERAR", rnsAlterar);

        regrasNegocio.put("EXCLUIR", rnsAlterar);
    }

    // ***********************************************************************


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

        if (tipoClienteOptional.isEmpty()) {
            TipoCliente novoTipoCliente = new TipoCliente(
                    1,
                    "NOVO",
                    "iniciante, primeira compra");
            tipoClienteDao.save(novoTipoCliente);
        }

        if (isDocumentAlreadyPresent.isPresent()) {
            throw new IllegalStateException("CPF já esta sendo " +
                    "usado por outro usuário");
        }

        if (isEmailAlreadyExist.isPresent()) {
            throw new IllegalStateException("E-mail já está sendo " +
                    "usado por um usuário no sistema");
        }

        String cpfTemp = cliente.getDocumentos()
                .stream().findFirst().get().getCodigo();

        if (!CpfValidator.isCPF(cpfTemp)) {
            throw new IllegalStateException("CPF inválido");
        }

        cliente.setTimeStamp(LocalDate.now());
        cliente.setAtivo(true);

        //TODO: rever esta regra de contro de tipo cliente
        cliente.setTipoCliente(this.tipoClienteDao.getOne(1));
        //TODO: implementar regra de obtenção de tipo documento

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

    public Optional<Cliente> findClienteByUsuarioId(int usuarioID) {
        return this.clienteDao.findClienteByUsuarioID(usuarioID);
    }

    public Resultado consultarClientePeloUsuarioID(int usuarioID){

//        return this.clienteDao.findClienteByUsuarioID(usuarioID);

        return null;
    }


    @Override
    public Resultado salvar(EntidadeDominio dominio) {
        Resultado resultado = new Resultado();
        List<IStrategy> rns = this.regrasNegocio.get("SALVAR");
        executarRegras(dominio, rns);
        if (sb.length() == 0) {
            this.clienteDao.saveAndFlush((Cliente) dominio);
            resultado.getEntidades().add(dominio);
        } else {
            resultado.getEntidades().add(dominio);
            resultado.setMensagem(sb.toString());
        }
        return resultado;
    }

    @Override
    public Resultado alterar(EntidadeDominio dominio) {
        return null;
    }

    @Override
    public Resultado excluir(EntidadeDominio dominio) {
        return null;
    }

    @Override
    public Resultado consultar(EntidadeDominio dominio) {
        return null;
    }

    @Override
    public Resultado visualizar(EntidadeDominio dominio) {
        return null;
    }

    public void executarRegras(EntidadeDominio dominio, List<IStrategy> rnsEntidade) {
        sb.setLength(0);
        for (IStrategy rn : rnsEntidade) {
            String msg = rn.processar(dominio);
            if (msg != null) {
                sb.append(msg);
            }
        }

    }

}
