package com.fatec.livrariaecommerce.models.domain;

public enum StatusPedido {
    AGUARDANDO_ENTREGA,
    ENTREGUE,
    TROCA_SOLICITADA,
    TROCA_RECUSADA,

    //OBS: Troca aceita, já pula direto para o status em TROCA
    TROCA_ACEITA,
    //ESSE CARA É IGUAL A:     TROCADO

    TROCA_AUTORIZADA,
    EM_TROCA,

}
