package com.fatec.livrariaecommerce.strategy.cliente.criptografia;

import com.fatec.livrariaecommerce.models.domain.Cliente;
import com.fatec.livrariaecommerce.models.domain.EntidadeDominio;
import com.fatec.livrariaecommerce.strategy.IStrategy;
import javax.crypto.Cipher;
import java.security.Key;
import java.util.Base64;

public class CriptografarSenha implements IStrategy {
    @Override
    public String processar(EntidadeDominio dominio) {

        Cliente cliente = (Cliente) dominio;
        String encodedPwd = "";
        try {
            Key key = GeradorChave.generateKey();
            Cipher c = Cipher.getInstance(GeradorChave.ALGORITHM);
            c.init(Cipher.ENCRYPT_MODE, key);
            byte[] encVal = c.doFinal(cliente.getUsuario().getSenha().getBytes());
            encodedPwd = Base64.getEncoder().encodeToString(encVal);

        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao tentar criptografar a senha!";
        }
        cliente.getUsuario().setSenha(encodedPwd);
        return null;
    }
}
