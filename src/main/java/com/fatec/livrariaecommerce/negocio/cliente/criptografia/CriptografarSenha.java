package com.fatec.livrariaecommerce.negocio.cliente.criptografia;

import com.fatec.livrariaecommerce.models.domain.Cliente;
import com.fatec.livrariaecommerce.models.domain.EntidadeDominio;
import com.fatec.livrariaecommerce.models.domain.Usuario;
import com.fatec.livrariaecommerce.negocio.IStrategy;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CriptografarSenha implements IStrategy {


    @Override
    public String processar(EntidadeDominio dominio) {

        Cliente cliente = (Cliente) dominio;

        MessageDigest algorithm = null;
        try {
            algorithm = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CriptografarSenha.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte messageDigest[] = null;
        try {
            messageDigest = algorithm.digest(cliente.getUsuario().getSenha().getBytes("UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(CriptografarSenha.class.getName()).log(Level.SEVERE, null, ex);
        }
        cliente.getUsuario().setSenha(messageDigest.toString());
        return null;
    }
}
