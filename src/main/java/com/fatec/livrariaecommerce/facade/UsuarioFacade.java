package com.fatec.livrariaecommerce.facade;

import com.fatec.livrariaecommerce.command.ExecutarRegras;
import com.fatec.livrariaecommerce.dao.UsuarioDao;
import com.fatec.livrariaecommerce.models.domain.Endereco;
import com.fatec.livrariaecommerce.models.domain.EntidadeDominio;
import com.fatec.livrariaecommerce.models.domain.Resultado;
import com.fatec.livrariaecommerce.models.domain.Usuario;
import com.fatec.livrariaecommerce.negocio.IStrategy;
import com.fatec.livrariaecommerce.negocio.cliente.criptografia.CriptografarSenha;
import com.fatec.livrariaecommerce.negocio.cliente.criptografia.DescriptografarSenha;
import com.fatec.livrariaecommerce.negocio.cliente.endereco.EnderecoValidaCep;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@RequiredArgsConstructor
@Service
public class UsuarioFacade implements IFacade {

    private final UsuarioDao usuarioDao;

    Map<String, List<IStrategy>> regrasNegocio = new HashMap<>();

    @PostConstruct
    public void postConstruct() {
        List<IStrategy> rnsConsultar = new ArrayList<>();
        regrasNegocio.put("CONSULTAR", rnsConsultar);
    }

    //    public Resultado findByEmailAndSenha(String email, String senha) {
    public Resultado findByEmailAndSenha(EntidadeDominio dominio) {
        Usuario usuario = (Usuario) dominio;
        Optional<Usuario> optionalUsuario;
        Resultado resultado = new Resultado();
        List<IStrategy> rns = this.regrasNegocio.get("CONSULTAR");
        StringBuilder sb = ExecutarRegras.executarRegras(usuario, rns);

        if (sb.length() == 0) {
//            String decodedSenha = DescriptografarSenha
//                    .decodeSenha(DescriptografarSenha.encodeSenha(usuario.getSenha()));
            String decodedSenha = DescriptografarSenha.encodeSenha(usuario.getSenha());
            optionalUsuario = this.usuarioDao.findByEmailAndSenha(usuario.getEmail(), decodedSenha);

            if (optionalUsuario.isEmpty()) {
                resultado.setMensagem("Erro ao recuperar usuário. Credenciais inválidas.");
            } else {
                resultado.getEntidades().add(optionalUsuario.get());
            }
        } else {
            resultado.getEntidades().add(dominio);
            resultado.setMensagem(sb.toString());
        }
        return resultado;
    }

    @Override
    public Resultado salvar(EntidadeDominio dominio) {
        return null;
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
