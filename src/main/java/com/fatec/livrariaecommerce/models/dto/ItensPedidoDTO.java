package com.fatec.livrariaecommerce.models.dto;


import com.fatec.livrariaecommerce.models.domain.ItensPedido;
import com.fatec.livrariaecommerce.models.domain.StatusPedido;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItensPedidoDTO {

    private int id;
    private int idLivro;
    private String nomeLivro;
    private int qtdComprada;
    private double valorUnitario;
    private double valorTotal;
    private StatusPedido statusPedido;
    private int quantidadeTrocada;

    public void fill(ItensPedido dominio) {

        StatusPedido initialStatusPedido = StatusPedido.AGUARDANDO_ENTREGA;

        dominio.atualizarDados(this.id, this.idLivro, this.nomeLivro, this.qtdComprada, this.valorUnitario,
                this.valorTotal, initialStatusPedido, this.quantidadeTrocada);
    }

    public static ItensPedidoDTO from(ItensPedido dominio) {
        ItensPedidoDTO dto = new ItensPedidoDTO();

        dto.id = dominio.getId();
        dto.idLivro = dominio.getIdLivro();
        dto.nomeLivro = dominio.getNomeLivro();
        dto.qtdComprada = dominio.getQtdComprada();
        dto.valorUnitario = dominio.getValorUnitario();
        dto.valorTotal = dominio.getValorTotal();
        dto.statusPedido = dominio.getStatusPedido();
        dto.quantidadeTrocada = dominio.getQuantidadeTrocada();
        return dto;
    }

}
