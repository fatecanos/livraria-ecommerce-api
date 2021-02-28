package com.fatec.livrariaecommerce.domain;

import javax.persistence.*;

@Entity
public class Usuario {
    @Id
    @SequenceGenerator(
            name="usuario_sequences",
            sequenceName="usuario_sequences",
            allocationSize=1
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public PerfilUsuario getPerfilUsuario() {
        return perfilUsuario;
    }

    public void setPerfilUsuario(PerfilUsuario perfilUsuario) {
        this.perfilUsuario = perfilUsuario;
    }
}
