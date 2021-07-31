package com.fatec.livrariaecommerce.models.dto;

import com.fatec.livrariaecommerce.models.domain.PerfilUsuario;
import com.fatec.livrariaecommerce.models.domain.Usuario;
import lombok.Getter;

@Getter
public class UsuarioDTO {
    private String email;
    private PerfilUsuario perfilUsuario;

    public UsuarioDTO(Usuario usuario) {
        this.email = usuario.getEmail();
        this.perfilUsuario = usuario.getPerfilUsuario();
    }
}
