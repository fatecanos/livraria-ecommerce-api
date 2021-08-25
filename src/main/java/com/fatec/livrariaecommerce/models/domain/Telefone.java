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
        super.setAtivo(true);
        if(this.getId() == 0 || this.getId() == null){
            super.setDataCriacao(LocalDateTime.now());
        }
        this.telefone = telefone;
    }

}
