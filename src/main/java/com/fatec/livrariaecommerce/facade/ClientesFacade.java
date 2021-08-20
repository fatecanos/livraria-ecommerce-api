package com.fatec.livrariaecommerce.facade;

import com.fatec.livrariaecommerce.command.ExecutarRegras;
import com.fatec.livrariaecommerce.dao.ClienteDao;
import com.fatec.livrariaecommerce.dao.DocumentoDao;
import com.fatec.livrariaecommerce.dao.TipoClienteDao;
import com.fatec.livrariaecommerce.dao.UsuarioDao;
import com.fatec.livrariaecommerce.models.domain.*;
import com.fatec.livrariaecommerce.negocio.IStrategy;
import com.fatec.livrariaecommerce.negocio.cliente.criptografia.CriptografarSenha;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@RequiredArgsConstructor
@Service
public class ClientesFacade implements IFacade {

    // ***********************************************************************

    private final ClienteDao clienteDao;
    private final TipoClienteDao tipoClienteDao;
    private final UsuarioDao usuarioDao;
    private final DocumentoDao documentoDao;

    Map<String, List<IStrategy>> regrasNegocio = new HashMap<>();

    // ***********************************************************************

    @PostConstruct
    public void postConstruct() {
        // Instanciar classes de regras de negocio e adicionar na lista de rns

        List<IStrategy> rnsSalvar = new ArrayList<>();
        rnsSalvar.add(new CriptografarSenha());
        regrasNegocio.put("SALVAR", rnsSalvar);


        List<IStrategy> rnsAlterar = new ArrayList<>();
        regrasNegocio.put("ALTERAR", rnsAlterar);

        List<IStrategy> rnsExcluir = new ArrayList<>();
        regrasNegocio.put("EXCLUIR", rnsExcluir);
    }

    // ***********************************************************************

    @Override
    public Resultado salvar(EntidadeDominio dominio) {

        Resultado resultado = new Resultado();
        List<IStrategy> rns = this.regrasNegocio.get("SALVAR");

        StringBuilder sb = ExecutarRegras.executarRegras(dominio, rns);

        if (sb.length() == 0) {
            this.clienteDao.saveAndFlush((Cliente) dominio);
            resultado.getEntidades().add(dominio);
        } else {
            resultado.getEntidades().add(dominio);
            resultado.setMensagem(sb.toString());
        }
        return resultado;
    }

    // ***********************************************************************

    @Override
    public Resultado alterar(EntidadeDominio dominio) {

        Cliente cliente = (Cliente) dominio;
        Resultado resultado = new Resultado();
        List<IStrategy> rns = this.regrasNegocio.get("ALTERAR");
        StringBuilder sb = ExecutarRegras.executarRegras(cliente, rns);

        if (sb.length() == 0) {
            this.clienteDao.saveAndFlush(cliente);
            resultado.getEntidades().add(cliente);
        } else {
            resultado.getEntidades().add(cliente);
            resultado.setMensagem(sb.toString());
        }
        return resultado;
    }

    // ***********************************************************************

    @Override
    public Resultado excluir(EntidadeDominio dominio) {
        Cliente cliente = (Cliente) dominio;
        Resultado resultado = new Resultado();
        List<IStrategy> rns = this.regrasNegocio.get("EXCLUIR");
        StringBuilder sb = ExecutarRegras.executarRegras(dominio, rns);
        if (sb.length() == 0) {
            cliente.setAtivo(false);
            cliente.getUsuario().setAtivo(false);
            this.clienteDao.saveAndFlush(cliente);
            resultado.getEntidades().add(dominio);
        } else {
            resultado.getEntidades().add(dominio);
            resultado.setMensagem(sb.toString());
        }
        return resultado;
    }

    // ***********************************************************************

    public Resultado findClienteByUsuarioId(int usuarioID) {
        System.out.println("Usuario ID: " + usuarioID);
        Resultado resultado = new Resultado();
        resultado.getEntidades().add(this.clienteDao.findClienteByUsuarioID(usuarioID).orElseThrow());
        return resultado;
    }

    // ***********************************************************************

    public Resultado ativarUsuario(EntidadeDominio dominio) {
        Cliente cliente = (Cliente) dominio;
        Resultado resultado = new Resultado();
        List<IStrategy> rns = this.regrasNegocio.get("EXCLUIR");
        StringBuilder sb = ExecutarRegras.executarRegras(dominio, rns);
        if (sb.length() == 0) {
            cliente.setAtivo(true);
            cliente.getUsuario().setAtivo(true);
            this.clienteDao.saveAndFlush(cliente);
            resultado.getEntidades().add(dominio);
        } else {
            resultado.getEntidades().add(dominio);
            resultado.setMensagem(sb.toString());
        }
        return resultado;
    }

    // ***********************************************************************

    public Resultado findUsuarioByID(int usuarioID) {
        System.out.println("Usuario ID: " + usuarioID);
        Resultado resultado = new Resultado();
        resultado.getEntidades().add(this.usuarioDao.getOne(usuarioID));
        return resultado;
    }

    // ***********************************************************************

    public Resultado consultarTodosClientes() {
        Resultado resultado = new Resultado();
        resultado.getEntidades().addAll(this.clienteDao.findAll());
        return resultado;
    }

    // ***********************************************************************

    @Override
    public Resultado consultar(EntidadeDominio dominio) {
        Cliente cliente = (Cliente) dominio;
        Resultado resultado = new Resultado();
        resultado.getEntidades().add(this.clienteDao.getOne(cliente.getId()));
        return resultado;
    }

    // ***********************************************************************

    @Override
    public Resultado visualizar(EntidadeDominio dominio) {
        return null;
    }

}
