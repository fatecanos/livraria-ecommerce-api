package com.fatec.livrariaecommerce.models.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cupom")
public class Cupom extends EntidadeDominio {

    private String nome;
    private double valor;
    private TipoCupom tipoCupom;

    @JoinColumn(name = "cliente", foreignKey = @ForeignKey(name = "fk_cupom_cliente"))
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    private Cliente cliente;

//    @JoinColumn(name = "venda", foreignKey = @ForeignKey(name = "fk_cupom_venda"))
//    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
//    private Venda venda;

//    public Cupom(Venda venda) {
//        this.venda = venda;
//    }

    public Cupom(Cliente cliente) {
        this.cliente = cliente;
    }

    public void atualizarDados(int id, String nome, double valor, TipoCupom tipoCupom) {
        super.setId(id);
        super.setAtivo(true);
        if (this.getId() == 0 || this.getId() == null) {
            super.setDataCriacao(LocalDateTime.now());
        }
        this.nome = nome;
        this.valor = valor;
        this.tipoCupom = tipoCupom;
    }

}


