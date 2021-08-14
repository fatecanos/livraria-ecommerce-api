package com.fatec.livrariaecommerce.facade;

import com.fatec.livrariaecommerce.dao.*;
import com.fatec.livrariaecommerce.models.domain.*;
import com.fatec.livrariaecommerce.models.domain.Endereco;
import com.fatec.livrariaecommerce.negocio.IStrategy;
import com.fatec.livrariaecommerce.negocio.endereco.EnderecoValidaCep;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor

@Service
public class EnderecoFacade implements IFacade{

    // ***********************************************************************

    private final EnderecoDao enderecoDao;
    private final ClienteEnderecoDao clienteEnderecoDao;
    private final CidadeDao cidadeDao;

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
    }

    // ***********************************************************************

    @Override
    public Resultado salvar(EntidadeDominio dominio) {
        Resultado resultado = new Resultado();

        // Recupera regras de negocio com base na operacao
        List<IStrategy> rns = this.regrasNegocio.get("SALVAR");

        // Executa regras de negocio
        executarRegras(dominio, rns);

        // Deu erro ?
        if (sb.length() == 0) {
            this.enderecoDao.saveAndFlush((Endereco) dominio);
            resultado.getEntidades().add(dominio);
        } else {
            resultado.getEntidades().add(dominio);
            resultado.setMensagem(sb.toString());
        }

        return resultado;
    }

    @Override
    public Resultado alterar(EntidadeDominio dominio) {
        Resultado resultado = new Resultado();

        // Recupera regras de negocio com base na operacao
        List<IStrategy> rns = this.regrasNegocio.get("ATUALIZAR");

        // Executa regras de negocio
        executarRegras(dominio, rns);

        // Deu erro ?
        if (sb.length() == 0) {
            this.enderecoDao.saveAndFlush((Endereco) dominio);
            resultado.getEntidades().add(dominio);
        } else {
            resultado.getEntidades().add(dominio);
            resultado.setMensagem(sb.toString());
        }

        return resultado;
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

    // ***********************************************************************

    public void executarRegras(EntidadeDominio dominio, List<IStrategy> rnsEntidade) {
        sb.setLength(0);
        for (IStrategy rn : rnsEntidade) {
            String msg = rn.processar(dominio);
            if (msg != null) {
                sb.append(msg);
            }
        }

    }

    // ***********************************************************************

}
