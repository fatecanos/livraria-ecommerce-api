package com.fatec.livrariaecommerce.models.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor

@Entity
@Table(name = "venda")
public class Venda extends EntidadeDominio {

    //todo: guardar endereço TROCAR DETACH E REFRESH
    //todo sem colocar WHERE(ativo = true)

    private int idEndereco;
    private double valorTotal;
    private String numero;
    private StatusVenda statusVenda;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "venda")
    @OrderBy(value = "id")
    private List<ItensPedido> itensPedidos;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "venda")
    @OrderBy(value = "id")
    private List<FormaPagamento> formaPagamentoList;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @OrderBy(value = "id")
    private List<Cupom> cupoms;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "cliente")
    private Cliente cliente;


    public void atualizarDados(int id, int idEndereco, Cliente cliente, double valorTotal, String numero, StatusVenda statusVenda, List<ItensPedido> itensPedidos,
                               List<FormaPagamento> formaPagamentoList, List<Cupom> cupoms) {
        super.setId(id);
        super.setAtivo(true);
        if (this.getId() == 0 || this.getId() == null) {
            super.setDataCriacao(LocalDateTime.now());
        }
        this.idEndereco = idEndereco;
        this.cliente = cliente;
        this.valorTotal = valorTotal;
        this.numero = numero;
        this.statusVenda = statusVenda;
        this.itensPedidos = itensPedidos;
        this.formaPagamentoList = formaPagamentoList;
        this.cupoms = cupoms;
    }
}
