package com.fatec.livrariaecommerce.models.dto;

import com.fatec.livrariaecommerce.models.domain.ItensPedido;
import com.fatec.livrariaecommerce.models.domain.Telefone;
import com.fatec.livrariaecommerce.models.domain.Venda;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class VendaDTO {

    private int id;
    private int idEndereco;
    private List<ItensPedidoDTO> itensPedido;

    public void fill(Venda dominio) {
        List<ItensPedido> itensPedidos = new ArrayList<>();
        if (!this.getItensPedido().isEmpty()) {
            for (ItensPedidoDTO itensPedidoDTO : this.getItensPedido()) {
                ItensPedido itensPedido = new ItensPedido(dominio);
                itensPedidoDTO.fill(itensPedido);
                itensPedidos.add(itensPedido);
            }

        }
        dominio.atualizarDados(this.id, this.idEndereco, itensPedidos);
    }

    public static VendaDTO from(Venda venda) {
        VendaDTO dto = new VendaDTO();
        dto.id = venda.getId();
        dto.idEndereco = venda.getIdEndereco();
        dto.itensPedido = venda.getItensPedidos().stream().map(ItensPedidoDTO::from).collect(Collectors.toList());
        return dto;
    }
}
