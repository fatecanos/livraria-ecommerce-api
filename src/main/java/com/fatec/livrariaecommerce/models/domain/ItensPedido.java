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
@Table(name = "itens_pedido")
public class ItensPedido extends EntidadeDominio {

    private int idLivro;

    @JoinColumn(name = "nome_livro")
    private String nomeLivro;
    private int qtdComprada;
    private double valorUnitario;
    private double valorTotal;
//    "situacao": ""

    @JoinColumn(name = "venda", foreignKey = @ForeignKey(name = "fk_itens_venda"))
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    private Venda venda;

    public ItensPedido(Venda venda){
        this.venda = venda;
    }

    public void atualizarDados(int id, int idLivro, String nomeLivro, int qtdComprada, double valorUnitario, double valorTotal){
        super.setId(id);
        super.setAtivo(true);
        if (this.getId() == 0 || this.getId() == null) {
            super.setDataCriacao(LocalDateTime.now());
        }
        this.idLivro = idLivro;
        this.nomeLivro = nomeLivro;
        this.qtdComprada = qtdComprada;
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorTotal;
    }
}
