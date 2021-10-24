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

    //todo: pensar na possibilidade de radio button com opção de selecionar ou digitar cupom

    private String nome;
    private double valor;
    private String codigo;
    private TipoCupom tipoCupom;

    @JoinColumn(name = "cliente", foreignKey = @ForeignKey(name = "fk_cupom_cliente"))
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    private Cliente cliente;

    //Armazenar origem do item de pedido (Saber qual a origem/criação do cupom)
    @JoinColumn(name = "itens_pedido", foreignKey = @ForeignKey(name = "fk_cupom_itens_pedido"))
    @OneToOne
    private ItensPedido itensPedido;

    //criar coluna

    public Cupom(Cliente cliente) {
        this.cliente = cliente;
    }

    public void atualizarDados(int id, String nome, double valor, String codigo, Cliente cliente, TipoCupom tipoCupom) {
        super.setId(id);
        super.setAtivo(true);
        if (this.getId() == 0 || this.getId() == null) {
            super.setDataCriacao(LocalDateTime.now());
        }
        this.cliente = cliente;
        this.codigo = codigo;
        this.nome = nome;
        this.valor = valor;
        this.tipoCupom = tipoCupom;
    }

}


