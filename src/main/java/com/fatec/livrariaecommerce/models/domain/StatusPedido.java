package com.fatec.livrariaecommerce.models.domain;

public enum StatusPedido {

    //TODO: CHANGE TO STATUS_PEDIDO TO FRONT
    //ENTREGUE INITIAL STATE
    //FEITO

    TROCA_SOLICITADA,
    TROCA_RECUSADA,
    TROCA_ACEITA,
    //ESSE CARA É IGUAL A:     TROCADO
    TROCA_AUTORIZADA,
    //OBS: Troca aceita, já pula direto para o status em TROCA
    EM_TROCA,

}
