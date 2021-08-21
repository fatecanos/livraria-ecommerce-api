package com.fatec.livrariaecommerce.facade;

import com.fatec.livrariaecommerce.dao.*;
import com.fatec.livrariaecommerce.models.domain.*;
import com.fatec.livrariaecommerce.negocio.IStrategy;
import com.fatec.livrariaecommerce.negocio.cliente.criptografia.CriptografarSenha;
import com.fatec.livrariaecommerce.negocio.cliente.criptografia.DescriptografarSenha;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Facade implements IFacade {

    // ***********************************************************************

    // key => Nome da classe, value => Instancia do dao
    private Map<String, IDAO> daos = new HashMap<>();

    // key => Nome da classe, value => Map( key => Operacao, value => Instancia da regra de negocio )
    Map<String, Map<String, List<IStrategy>>> regrasNegocio = new HashMap<>();

    // ***********************************************************************

    public Facade(ClienteDao clienteDao, UsuarioDao usuarioDao, EnderecoDao enderecoDao, CidadeDao cidadeDao,
                  TipoEnderecoDao tipoEnderecoDao, TelefoneDao telefoneDao) {

        // ClienteDao
        this.daos.put(Cliente.class.getName(), clienteDao);

        // UsuarioDao
        this.daos.put(Usuario.class.getName(), usuarioDao);

        // EnderecoDao
        this.daos.put(Endereco.class.getName(), enderecoDao);

        // CidadeDao
        this.daos.put(Cidade.class.getName(), cidadeDao);

        // TipoEnderecoDao
        this.daos.put(TipoEndereco.class.getName(), tipoEnderecoDao);

        // TelefoneDao
        this.daos.put(Telefone.class.getName(), telefoneDao);

    }

    // ***********************************************************************

    @PostConstruct
    public void postConstruct() {

        // ***********************************************************************
        // Cliente
        // ***********************************************************************

        Map<String, List<IStrategy>> regrasNegocioCliente = new HashMap<>();

        // Instanciar classes de regras de negocio e adicionar na lista de rns
        List<IStrategy> rnsSalvarCliente = new ArrayList<>();
        rnsSalvarCliente.add(new CriptografarSenha());
        regrasNegocioCliente.put("SALVAR", rnsSalvarCliente);

        List<IStrategy> rnsAlterarCliente = new ArrayList<>();
        regrasNegocioCliente.put("ALTERAR", rnsAlterarCliente);

        List<IStrategy> rnsExcluirCliente = new ArrayList<>();
        regrasNegocioCliente.put("EXCLUIR", rnsExcluirCliente);

        this.regrasNegocio.put(Cliente.class.getName(), regrasNegocioCliente);

        // ***********************************************************************

        // ***********************************************************************
        // Endereco
        // ***********************************************************************

        Map<String, List<IStrategy>> regrasNegocioEndereco = new HashMap<>();

        // Instanciar classes de regras de negocio e adicionar na lista de rns
        List<IStrategy> rnsSalvarEndereco = new ArrayList<>();
        regrasNegocioEndereco.put("SALVAR", rnsSalvarEndereco);

        List<IStrategy> rnsAlterarEndereco = new ArrayList<>();
        regrasNegocioEndereco.put("ALTERAR", rnsAlterarEndereco);

        List<IStrategy> rnsExcluirEndereco = new ArrayList<>();
        regrasNegocioEndereco.put("EXCLUIR", rnsExcluirEndereco);

        this.regrasNegocio.put(Endereco.class.getName(), regrasNegocioEndereco);

        // ***********************************************************************

        // Telefone
        // ***********************************************************************

        Map<String, List<IStrategy>> regrasNegocioTelefone = new HashMap<>();

        // Instanciar classes de regras de negocio e adicionar na lista de rns
        List<IStrategy> rnsSalvarTelefone = new ArrayList<>();
        regrasNegocioTelefone.put("SALVAR", rnsSalvarTelefone);

        List<IStrategy> rnsAlterarTelefone = new ArrayList<>();
        regrasNegocioTelefone.put("ALTERAR", rnsAlterarTelefone);

        List<IStrategy> rnsExcluirTelefone = new ArrayList<>();
        regrasNegocioTelefone.put("EXCLUIR", rnsExcluirTelefone);

        this.regrasNegocio.put(Telefone.class.getName(), regrasNegocioTelefone);

        // ***********************************************************************
        // Usuario
        // ***********************************************************************

        Map<String, List<IStrategy>> regrasNegocioUsuario = new HashMap<>();

        // Instanciar classes de regras de negocio e adicionar na lista de rns

        List<IStrategy> rnsConsultarUsuario = new ArrayList<>();
        rnsConsultarUsuario.add(new DescriptografarSenha());
        regrasNegocioUsuario.put("CONSULTAR", rnsConsultarUsuario);

        this.regrasNegocio.put(Usuario.class.getName(), regrasNegocioUsuario);
    }

    // ***********************************************************************

    @Override
    public Resultado salvar(EntidadeDominio dominio) {
        Resultado resultado = new Resultado();
        List<IStrategy> rns = this.regrasNegocio.get(dominio.getClass().getName()).get("SALVAR");

        StringBuilder sb = this.executarRegras(dominio, rns);

        if (sb.length() == 0) {
            this.daos.get(dominio.getClass().getName()).salvar(dominio);
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
        List<IStrategy> rns = this.regrasNegocio.get(dominio.getClass().getName()).get("ALTERAR");

        StringBuilder sb = this.executarRegras(dominio, rns);

        if (sb.length() == 0) {
            this.daos.get(dominio.getClass().getName()).salvar(dominio);
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
        Resultado resultado = new Resultado();
        List<IStrategy> rns = this.regrasNegocio.get(dominio.getClass().getName()).get("CONSULTAR");
        if(rns.size() > 0){
            StringBuilder sb = this.executarRegras(dominio, rns);
        }

        resultado.getEntidades().addAll(this.daos.get(dominio.getClass().getName()).consultar(dominio));
        return resultado;
    }

//    visualizar é semelhante ao getOne();
    @Override
    public Resultado visualizar(EntidadeDominio dominio) {
        return null;
    }

    // ***********************************************************************

    public StringBuilder executarRegras(EntidadeDominio dominio, List<IStrategy> rnsEntidade) {
        StringBuilder sb = new StringBuilder();

        for (IStrategy rn : rnsEntidade) {
            String msg = rn.processar(dominio);
            if (msg != null) {
                sb.append(msg);
            }
        }
        return sb;
    }

    // ***********************************************************************


}
