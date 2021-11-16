package com.fatec.livrariaecommerce.models.dto;

import com.fatec.livrariaecommerce.models.domain.LivroFaturamentoMensal;
import com.fatec.livrariaecommerce.models.domain.RankCliente;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RankClienteDTO {

    private Integer idCliente;
    private String nomeCliente;
    private String cpfCliente;
    private int comprasRealizadas;

    public static RankClienteDTO from(RankCliente rankCliente) {
        RankClienteDTO dto = new RankClienteDTO();
        dto.idCliente = rankCliente.getIdCliente();
        dto.nomeCliente = rankCliente.getNomeCliente();
        dto.cpfCliente = rankCliente.getCpfCliente();
        dto.comprasRealizadas = rankCliente.getComprasRealizadas();
        return dto;
    }
}
