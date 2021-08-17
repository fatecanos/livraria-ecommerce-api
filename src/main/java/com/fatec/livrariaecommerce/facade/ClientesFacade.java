package com.fatec.livrariaecommerce.facade;

import com.fatec.livrariaecommerce.dao.ClienteDao;
import com.fatec.livrariaecommerce.dao.DocumentoDao;
import com.fatec.livrariaecommerce.dao.TipoClienteDao;
import com.fatec.livrariaecommerce.dao.UsuarioDao;
import com.fatec.livrariaecommerce.models.domain.*;
import com.fatec.livrariaecommerce.models.utils.CpfValidator;
import com.fatec.livrariaecommerce.negocio.IStrategy;
import com.fatec.livrariaecommerce.negocio.cliente.criptografia.CriptografarSenha;
import com.fatec.livrariaecommerce.negocio.cliente.endereco.EnderecoValidaCep;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.*;

@RequiredArgsConstructor
@Service
public class ClientesFacade implements IFacade {

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
        // Instanciar classes de regras de negocio e adicionar na lista de rns
        List<IStrategy> rnsSalvar = new ArrayList<>();

        rnsSalvar.add(new CriptografarSenha());

        regrasNegocio.put("SALVAR", rnsSalvar);


        List<IStrategy> rnsAlterar = new ArrayList<>();
        rnsAlterar.add(new CriptografarSenha());


        regrasNegocio.put("ALTERAR", rnsAlterar);

        regrasNegocio.put("EXCLUIR", rnsAlterar);
    }

    // ***********************************************************************

//    public Cliente disableById(int id) {
//        Cliente clienteTemp = this.clienteDao.getOne(id);
//        clienteTemp.setAtivo(false);
//        return this.clienteDao.save(clienteTemp);
//    }

//    public Cliente findClienteById(int id) {
//        return this.clienteDao.getOne(id);
//    }

    public Optional<Cliente> findClienteByUsuarioId(int usuarioID) {
        return this.clienteDao.findClienteByUsuarioID(usuarioID);
    }

    @Override
    public Resultado salvar(EntidadeDominio dominio) {

        Cliente cliente = (Cliente) dominio;

        System.out.println("ME PRINTA O USER: " + cliente.getCpf());

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
