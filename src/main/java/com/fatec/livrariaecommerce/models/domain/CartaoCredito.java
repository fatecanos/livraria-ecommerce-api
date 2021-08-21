package com.fatec.livrariaecommerce.models.domain;

import javax.persistence.*;

@Entity
public class CartaoCredito extends EntidadeDominio {
//    @Id
//    @SequenceGenerator(
//            name="cartoes_credito_sequence",
//            sequenceName="cartoes_credito_sequence",
//            allocationSize=1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "cartoes_credito_sequence"
//    )
//    private int id;
    private String numero;
    private String bandeira;
    private boolean isPreferencial;
    @OneToOne
    private Endereco enderecoCobranca;
}
