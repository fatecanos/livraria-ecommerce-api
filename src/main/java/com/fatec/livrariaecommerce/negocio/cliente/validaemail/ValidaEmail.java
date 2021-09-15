package com.fatec.livrariaecommerce.negocio.cliente.validaemail;

import com.fatec.livrariaecommerce.facade.Facade;
import com.fatec.livrariaecommerce.models.domain.Cliente;
import com.fatec.livrariaecommerce.models.domain.EntidadeDominio;
import com.fatec.livrariaecommerce.models.domain.Resultado;
import com.fatec.livrariaecommerce.models.domain.Usuario;
import com.fatec.livrariaecommerce.negocio.IStrategy;
import org.springframework.http.ResponseEntity;

public class ValidaEmail implements IStrategy {

    private final Facade facade;

    public ValidaEmail(Facade facade) {
        this.facade = facade;
    }

    @Override
    public String processar(EntidadeDominio dominio) {
        Usuario usuario = new Usuario();
        Cliente cliente = (Cliente) dominio;
        usuario.setEmail(cliente.getUsuario().getEmail());

        Resultado usuarioResultado = this.facade.consultar(usuario);
        if (!usuarioResultado.getEntidades().isEmpty()) {
            return "Este email já está cadastrado";
        }
        return "";
    }
}
