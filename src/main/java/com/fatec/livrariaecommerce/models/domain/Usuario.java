package com.fatec.livrariaecommerce.models.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class Usuario extends EntidadeDominio {
    @Id
    @SequenceGenerator(
            name = "usuario_sequences",
            sequenceName = "usuario_sequences",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "usuario_sequences"
    )
    private int id;
    private String email;
    private String senha;
    @Enumerated(EnumType.STRING)
    private PerfilUsuario perfilUsuario;

    public Usuario() {

    }

    public Usuario(String email, String senha, PerfilUsuario perfilUsuario) {
        this.email = email;
        this.senha = senha;
        this.perfilUsuario = perfilUsuario;
    }

    public void atualizarDados(String email, String senha){
        this.email = email;
        this.senha = senha;
    }

}
