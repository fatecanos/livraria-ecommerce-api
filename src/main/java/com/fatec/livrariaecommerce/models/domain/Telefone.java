package com.fatec.livrariaecommerce.models.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Telefone extends EntidadeDominio{

    // ***********************************************************************

    String telefone;

    @JoinColumn(name = "cliente", foreignKey = @ForeignKey(name = "fk_telefone_cliente"))
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    private Cliente cliente;

    // ***********************************************************************

    public Telefone(Cliente cliente){
        this.cliente = cliente;
    }

    public void atualizarDados(int id, String telefone){
        super.setId(id);
        this.telefone = telefone;
    }

}
