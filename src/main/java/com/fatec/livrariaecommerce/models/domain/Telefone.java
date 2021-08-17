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

    @Id
    @SequenceGenerator(name = "telefone_sequence", sequenceName = "telefone_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "telefone_sequence")
    int id;
    String telefone;

    @JoinColumn(name = "cliente", foreignKey = @ForeignKey(name = "fk_telefone_cliente"))
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    private Cliente cliente;

    // ***********************************************************************

    public Telefone(Cliente cliente){
        this.cliente = cliente;
    }

    public void atualizarDados(int id, String telefone){
        this.id = id;
        this.telefone = telefone;
    }

}
