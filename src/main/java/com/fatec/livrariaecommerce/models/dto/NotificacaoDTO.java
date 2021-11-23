package com.fatec.livrariaecommerce.models.dto;

import com.fatec.livrariaecommerce.models.domain.Notificacao;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NotificacaoDTO {

    private int id;
    private String conteudo;
    private boolean lida;

    public void fill(Notificacao dominio) {
        dominio.atualizarDados(this.id, this.conteudo, this.lida);
    }

    public static NotificacaoDTO from(Notificacao notificacao){
        NotificacaoDTO dto = new NotificacaoDTO();
        dto.id = notificacao.getId();
        dto.conteudo = notificacao.getConteudo();
        dto.lida = notificacao.isLida();
        return dto;
    }

}
