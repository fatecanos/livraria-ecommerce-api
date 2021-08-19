package com.fatec.livrariaecommerce.models.dto;

import com.fatec.livrariaecommerce.models.domain.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    private String email;
    private String senha;

    public void fill(Usuario usuario){
        usuario.atualizarDados(this.email, this.senha);
    }

    public LoginDTO from(Usuario usuario){
        LoginDTO dto = new LoginDTO();
        dto.email = usuario.getEmail();
        dto.senha = usuario.getSenha();
        return dto;
    }
}
