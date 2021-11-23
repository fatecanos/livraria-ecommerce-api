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
public class Notificacao extends EntidadeDominio {

    private String conteudo;

    @JoinColumn(name = "cliente", foreignKey = @ForeignKey(name = "fk_notificacao_cliente"))
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    private Cliente cliente;

    @JoinColumn(name = "itens_pedido", foreignKey = @ForeignKey(name = "fk_notificacao_itens_pedido"))
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    private ItensPedido itensPedido;
    private boolean lida;


    public Notificacao(Cliente cliente, ItensPedido itensPedido){
        this.cliente = cliente;
        this.itensPedido = itensPedido;
    }

    public void atualizarDados(int id, String conteudo, boolean lida){
        super.setId(id);
        super.setAtivo(true);
        if(this.getId() == 0 || this.getId() == null){
            super.setDataCriacao(LocalDateTime.now());
        }
        this.conteudo = conteudo;
        this.lida = lida;
    }

}
