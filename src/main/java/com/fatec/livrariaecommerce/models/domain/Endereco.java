package com.fatec.livrariaecommerce.models.domain;

import com.fatec.livrariaecommerce.models.domain.Cidade;
import com.fatec.livrariaecommerce.models.domain.Cliente;
import com.fatec.livrariaecommerce.models.domain.EntidadeDominio;
import com.fatec.livrariaecommerce.models.domain.TipoEndereco;
import com.fatec.livrariaecommerce.models.dto.EnderecoDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor

@Getter
@Setter

@Entity
public class Endereco extends EntidadeDominio {

    // ***********************************************************************

    private String nome;
    private String logradouro;
    private String tipoLogradouro;
    private String bairro;
    private String numero;
    private String cep;
    private String complemento;
    private String pais;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "tipo_endereco_id")
    private TipoEndereco tipoEndereco;

    @JoinColumn(name = "cliente", foreignKey = @ForeignKey(name = "fk_endereco_cliente"))
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    private Cliente cliente;

    // ***********************************************************************

    public Endereco(Cliente cliente) {
        this.cliente = cliente;
    }

    // ***********************************************************************

    public void atualizarDados(int id, String nome, String logradouro, String bairro, String numero, String cep,
                               String complemento, String pais, String tipoLogradouro, Cliente cliente, Cidade cidade, TipoEndereco tipoEndereco) {
        super.setId(id);
        super.setAtivo(true);
        if (this.getId() == 0 || this.getId() == null) {
            super.setDataCriacao(LocalDateTime.now());
        }
        this.nome = nome;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.numero = numero;
        this.cep = cep;
        this.complemento = complemento;
        this.cliente = cliente;
        this.cidade = cidade;
        this.tipoEndereco = tipoEndereco;
        this.tipoLogradouro = tipoLogradouro;
        this.pais = pais;
    }

    // ***********************************************************************
}
