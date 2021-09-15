package com.fatec.livrariaecommerce.models.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Usuario extends EntidadeDominio {

    private String email;
    private String senha;
    @Enumerated(EnumType.STRING)
    private PerfilUsuario perfilUsuario;

    public Usuario() {

    }

    public Usuario(String email, String senha, PerfilUsuario perfilUsuario) {
        super.setAtivo(true);
        if (this.getId() == null) {
            super.setDataCriacao(LocalDateTime.now());
        }
        this.email = email;
        this.senha = senha;
        this.perfilUsuario = perfilUsuario;
    }

    public void atualizarDados(String email, String senha){
        this.email = email;
        this.senha = senha;
    }

}
