package com.fatec.livrariaecommerce.models.dto;

import com.fatec.livrariaecommerce.models.domain.FormaPagamento;
import com.fatec.livrariaecommerce.models.domain.ItensPedido;
import com.fatec.livrariaecommerce.models.domain.Telefone;
import com.fatec.livrariaecommerce.models.domain.Venda;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class VendaDTO {

    private int id;

    @JoinColumn(name = "id_endereco")
    private int idEndereco;

    private List<ItensPedidoDTO> itensPedido;
    private List<FormaPagamentoDTO> formasPagamento;

    //id_cliente

    public void fill(Venda dominio) {
        List<ItensPedido> itensPedidos = new ArrayList<>();
        List<FormaPagamento> formaPagamentoList = new ArrayList<>();

        if (!this.getFormasPagamento().isEmpty()) {
            for (FormaPagamentoDTO formaPagamentoDTOs : this.getFormasPagamento()) {
                FormaPagamento formaPagamento = new FormaPagamento(dominio);
                formaPagamentoDTOs.fill(formaPagamento);
                formaPagamentoList.add(formaPagamento);
            }
        }

        if (!this.getItensPedido().isEmpty()) {
            for (ItensPedidoDTO itensPedidoDTO : this.getItensPedido()) {
                ItensPedido itensPedido = new ItensPedido(dominio);
                itensPedidoDTO.fill(itensPedido);
                itensPedidos.add(itensPedido);
            }
        }
        dominio.atualizarDados(this.id, this.idEndereco, itensPedidos, formaPagamentoList);
    }

    public static VendaDTO from(Venda venda) {
        VendaDTO dto = new VendaDTO();
        dto.id = venda.getId();
        dto.idEndereco = venda.getIdEndereco();
        dto.itensPedido = venda.getItensPedidos().stream().map(ItensPedidoDTO::from).collect(Collectors.toList());
        dto.formasPagamento = venda.getFormaPagamentoList().stream().map(FormaPagamentoDTO::from).collect(Collectors.toList());
        return dto;
    }
}
