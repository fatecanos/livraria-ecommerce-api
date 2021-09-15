package com.fatec.livrariaecommerce.negocio.cliente.criptografia;
import com.fatec.livrariaecommerce.models.domain.Cliente;
import com.fatec.livrariaecommerce.models.domain.EntidadeDominio;
import com.fatec.livrariaecommerce.models.domain.Usuario;
import com.fatec.livrariaecommerce.negocio.IStrategy;
import javax.crypto.Cipher;
import java.security.Key;
import java.util.Base64;


//https://stackoverflow.com/a/54905278/10952599
public class DescriptografarSenha implements IStrategy{

    public static String decodeSenha(String senha){
        String decodedPWD = "";
        try {
            Key key = GeradorChave.generateKey();
            Cipher c = Cipher.getInstance(GeradorChave.ALGORITHM);
            c.init(Cipher.DECRYPT_MODE, key);
            byte[] decordedValue = Base64.getDecoder().decode(senha);
            byte[] decValue = c.doFinal(decordedValue);
            decodedPWD = new String(decValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decodedPWD;
    }

    public static String encodeSenha(String senha){
        String encodedPwd = "";
        try {
            Key key = GeradorChave.generateKey();
            Cipher c = Cipher.getInstance(GeradorChave.ALGORITHM);
            c.init(Cipher.ENCRYPT_MODE, key);
            byte[] encVal = c.doFinal(senha.getBytes());
            encodedPwd = Base64.getEncoder().encodeToString(encVal);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodedPwd;
    }

    @Override
    public String processar(EntidadeDominio dominio) {
        Usuario usuario = (Usuario) dominio;
        if(usuario.getSenha() != null){
            usuario.setSenha(encodeSenha(usuario.getSenha()));
        }
        return null;
    }
}
