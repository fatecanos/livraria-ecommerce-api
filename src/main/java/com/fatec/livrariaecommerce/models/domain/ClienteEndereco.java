package com.fatec.livrariaecommerce.models.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "cliente_endereco")
public class ClienteEndereco extends EntidadeDominio {

//    @Id
//    private int id;

    @Column(name = "cliente_id")
    private int clienteId;

    @Column(name = "enderecos_id")
    private int enderecosId;

    public ClienteEndereco() {

    }

    public ClienteEndereco(int clienteId, int enderecoId) {
        this.clienteId = clienteId;
        this.enderecosId = enderecoId;
    }
}
