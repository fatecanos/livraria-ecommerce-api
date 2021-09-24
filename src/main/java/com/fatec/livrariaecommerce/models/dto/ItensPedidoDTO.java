package com.fatec.livrariaecommerce.models.dto;


import com.fatec.livrariaecommerce.models.domain.ItensPedido;
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

//    transformar em regra de negócio

    public void fill(ItensPedido dominio) {
        dominio.atualizarDados(this.id, this.idLivro, this.nomeLivro, this.qtdComprada, this.valorUnitario,
                this.valorTotal);

//        dominio.atualizarDados(this.id, this.idLivro, this.qtdComprada);
    }

    public static ItensPedidoDTO from(ItensPedido dominio) {
        ItensPedidoDTO dto = new ItensPedidoDTO();

        dto.id = dominio.getId();
        dto.idLivro = dominio.getIdLivro();
        dto.nomeLivro = dominio.getNomeLivro();
        dto.qtdComprada = dominio.getQtdComprada();
        dto.valorUnitario = dominio.getValorUnitario();
        dto.valorTotal = dominio.getValorTotal();

        return dto;
    }

}
