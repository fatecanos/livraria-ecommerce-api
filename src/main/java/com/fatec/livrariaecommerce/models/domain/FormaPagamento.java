package com.fatec.livrariaecommerce.models.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor

@Setter
@Getter
@Entity
@Table(name = "forma_pagamento")
public class FormaPagamento extends EntidadeDominio {

    private int idCartao;
    private double valorPago;

    @JoinColumn(name = "venda", foreignKey = @ForeignKey(name = "fk_forma_pagamento_venda"))
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    private Venda venda;

    public FormaPagamento(Venda venda) {
        this.venda = venda;
    }

    public void atualizarDados(int id, int idCartao, double valorPago) {
        super.setId(id);
        super.setAtivo(true);
        if (this.getId() == 0 || this.getId() == null) {
            super.setDataCriacao(LocalDateTime.now());
        }
        this.idCartao = idCartao;
        this.valorPago = valorPago;
    }



}
