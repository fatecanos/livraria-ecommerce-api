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

    private int idEndereco;
    private double valorTotal;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "venda")
    @OrderBy(value = "id")
    private List<ItensPedido> itensPedidos;

    public void atualizarDados(int id, int idEndereco, List<ItensPedido> itensPedidos){
        super.setId(id);
        super.setAtivo(true);
        if (this.getId() == 0 || this.getId() == null) {
            super.setDataCriacao(LocalDateTime.now());
        }
        this.idEndereco = idEndereco;
        this.itensPedidos = itensPedidos;
    }
}
