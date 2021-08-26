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
@Table(name = "cartao_credito")
public class CartaoCredito extends EntidadeDominio {

    private String bandeira;
    private boolean isPreferencial;
    private String codigoSeguranca;
    private String nomeImpressoCartao;
    private String numeroCartao;

    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;

    @JoinColumn(name = "cliente", foreignKey = @ForeignKey(name = "fk_cartao_credito_cliente"))
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    private Cliente cliente;

    public CartaoCredito(Cliente cliente) {
        this.cliente = cliente;
    }

    public void atualizarDados(int id, String bandeira, String codigoSeguranca, String nomeImpressoCartao,
                               String numeroCartao, boolean isPreferencial, Endereco endereco) {

        super.setId(id);
        super.setAtivo(true);
        if (this.getId() == 0 || this.getId() == null) {
            super.setDataCriacao(LocalDateTime.now());
        }
        this.bandeira = bandeira;
        this.codigoSeguranca = codigoSeguranca;
        this.nomeImpressoCartao = nomeImpressoCartao;
        this.numeroCartao = numeroCartao;
        this.isPreferencial = isPreferencial;
        this.endereco = endereco;
    }

}


