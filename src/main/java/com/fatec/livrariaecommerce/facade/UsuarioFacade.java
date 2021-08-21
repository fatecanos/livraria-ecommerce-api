package com.fatec.livrariaecommerce.facade;

import com.fatec.livrariaecommerce.dao.UsuarioDao;
import com.fatec.livrariaecommerce.models.domain.EntidadeDominio;
import com.fatec.livrariaecommerce.models.domain.Resultado;
import com.fatec.livrariaecommerce.models.domain.Usuario;
import com.fatec.livrariaecommerce.negocio.IStrategy;
import com.fatec.livrariaecommerce.negocio.cliente.criptografia.DescriptografarSenha;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@RequiredArgsConstructor
@Service
public class UsuarioFacade implements IFacade {

    private final UsuarioDao usuarioDao;
    Map<String, List<IStrategy>> regrasNegocio = new HashMap<>();

    // ***********************************************************************


    @PostConstruct
    public void postConstruct() {
        List<IStrategy> rnsConsultar = new ArrayList<>();
        regrasNegocio.put("CONSULTAR", rnsConsultar);
    }

    // ***********************************************************************

//    public Resultado findByEmailAndSenha(EntidadeDominio dominio) {
//        Usuario usuario = (Usuario) dominio;
//        Optional<Usuario> optionalUsuario;
//        Resultado resultado = new Resultado();
//        List<IStrategy> rns = this.regrasNegocio.get("CONSULTAR");
//        StringBuilder sb = ExecutarRegras.executarRegras(usuario, rns);
//
//        if (sb.length() == 0) {
////            String decodedSenha = DescriptografarSenha
////                    .decodeSenha(DescriptografarSenha.encodeSenha(usuario.getSenha()));
//            String decodedSenha = DescriptografarSenha.encodeSenha(usuario.getSenha());
//
////            optionalUsuario = this.usuarioDao.findByEmailAndSenha(usuario.getEmail(), decodedSenha);
//
//            if (optionalUsuario.isEmpty()) {
//                resultado.setMensagem("Erro ao recuperar usuário. Credenciais inválidas.");
//            } else {
//                if(optionalUsuario.get().isAtivo()){
//                    resultado.getEntidades().add(optionalUsuario.get());
//                }else{
//                    resultado.setMensagem("A conta do usuário está desativada.");
//                }
//            }
//        } else {
////            resultado.getEntidades().add(dominio);
//            resultado.setMensagem(sb.toString());
//        }
//        return resultado;
//    }

    // ***********************************************************************


    @Override
    public Resultado salvar(EntidadeDominio dominio) {
        return null;
    }

    // ***********************************************************************

    @Override
    public Resultado alterar(EntidadeDominio dominio) {
        return null;
    }

    // ***********************************************************************

    @Override
    public Resultado excluir(EntidadeDominio dominio) {
        return null;
    }

    // ***********************************************************************

    @Override
    public Resultado consultar(EntidadeDominio dominio) {
        return null;
    }

    // ***********************************************************************

    @Override
    public Resultado visualizar(EntidadeDominio dominio) {
        return null;
    }
}
