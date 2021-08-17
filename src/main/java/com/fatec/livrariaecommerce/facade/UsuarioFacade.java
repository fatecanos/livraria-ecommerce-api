package com.fatec.livrariaecommerce.facade;

import com.fatec.livrariaecommerce.command.ExecutarRegras;
import com.fatec.livrariaecommerce.dao.UsuarioDao;
import com.fatec.livrariaecommerce.models.domain.Endereco;
import com.fatec.livrariaecommerce.models.domain.EntidadeDominio;
import com.fatec.livrariaecommerce.models.domain.Resultado;
import com.fatec.livrariaecommerce.models.domain.Usuario;
import com.fatec.livrariaecommerce.negocio.IStrategy;
import com.fatec.livrariaecommerce.negocio.cliente.criptografia.CriptografarSenha;
import com.fatec.livrariaecommerce.negocio.cliente.endereco.EnderecoValidaCep;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@RequiredArgsConstructor
@Service
public class UsuarioFacade implements IFacade{

    private final UsuarioDao usuarioDao;

    Map<String, List<IStrategy>> regrasNegocio = new HashMap<>();

    @PostConstruct
    public void postConstruct() {
        List<IStrategy> rnsSalvar = new ArrayList<>();
        // Instanciar classes de regras de negocio e adicionar na lista de rns
        rnsSalvar.add(new CriptografarSenha());
        regrasNegocio.put("SALVAR", rnsSalvar);
    }

    public Optional<Usuario> findByEmailAndSenha(String email, String senha) {
        return this.usuarioDao.findByEmailAndSenha(email, senha);
    }


    @Override
    public Resultado salvar(EntidadeDominio dominio) {
        Resultado resultado = new Resultado();

        // Recupera regras de negocio com base na operacao
        List<IStrategy> rns = this.regrasNegocio.get("SALVAR");

        // Executa regras de negocio
        StringBuilder sb = ExecutarRegras.executarRegras(dominio, rns);

        // Deu erro ?
        if (sb.length() == 0) {

            this.usuarioDao.saveAndFlush((Usuario) dominio);

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
}
