package com.fatec.livrariaecommerce.models.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter

@MappedSuperclass
public abstract class EntidadeDominio {

    @Access(AccessType.FIELD)
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @javax.persistence.Id
    private int id;

    @CreatedDate
    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @Basic
    @Column(name = "ativo", nullable = false)
    private boolean ativo;


    public EntidadeDominio() {
        this.dataCriacao = LocalDateTime.now();
        this.ativo = true;
    }
}
