package com.fatec.livrariaecommerce.models.domain;

public enum StatusPedido {
    PAGAMENTO_REPROVADO,
    AGUARDANDO_ENTREGA,
    ENTREGUE,
    TROCA_SOLICITADA,
    TROCA_RECUSADA,

    //OBS: Troca aceita, já pula direto para o status em TROCA
    TROCA_ACEITA,

    //ALTERAR DIRETAMENTE APÓS TROCA_ACEITA
    EM_TROCA,

    //ESSE CARA É IGUAL A:     TROCADO
    TROCA_AUTORIZADA,



}
