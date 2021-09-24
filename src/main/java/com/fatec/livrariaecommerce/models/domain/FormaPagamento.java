package com.fatec.livrariaecommerce.models.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor

@Setter
@Getter
@Entity
@Table(name = "forma_pagamento")
public class FormaPagamento extends EntidadeDominio{

    private int idCartao;
    private double valorPago;

}
