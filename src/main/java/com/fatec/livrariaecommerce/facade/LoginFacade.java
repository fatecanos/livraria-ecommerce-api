package com.fatec.livrariaecommerce.facade;

import com.fatec.livrariaecommerce.dao.UsuarioDao;
import com.fatec.livrariaecommerce.models.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginFacade {

    @Autowired
    private UsuarioDao usuarioDao;


    public void save() {

    }

    public Optional<Usuario> findByEmailAndSenha(String email, String senha) {
        return this.usuarioDao.findByEmailAndSenha(email, senha);
    }
}
