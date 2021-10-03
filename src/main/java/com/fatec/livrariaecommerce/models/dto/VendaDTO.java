package com.fatec.livrariaecommerce.models.dto;

import com.fatec.livrariaecommerce.models.domain.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class VendaDTO {

    private int id;

    private int idEndereco;
    private int idCliente;
    private double valorTotal;
    private String numero;
    private StatusVenda status;
    private LocalDateTime dataCriacao;
    private List<ItensPedidoDTO> itensPedido;
    private List<FormaPagamentoDTO> formasPagamento;
    private List<CupomDTO> cupoms;

    public void fill(Venda dominio) {
        List<ItensPedido> itensPedidos = new ArrayList<>();
        List<FormaPagamento> formaPagamentoList = new ArrayList<>();
        List<Cupom> cupomList = new ArrayList<>();
        Cliente cliente = new Cliente();
        cliente.setId(idCliente);
        StatusVenda statusVenda = this.status;

        if (!this.getItensPedido().isEmpty()) {
            for (ItensPedidoDTO itensPedidoDTO : this.getItensPedido()) {
                ItensPedido itensPedido = new ItensPedido(dominio);
                itensPedidoDTO.fill(itensPedido);
                itensPedidos.add(itensPedido);
            }
        }

        if (!this.getFormasPagamento().isEmpty()) {
            for (FormaPagamentoDTO formaPagamentoDTOs : this.getFormasPagamento()) {
                FormaPagamento formaPagamento = new FormaPagamento(dominio);
                formaPagamentoDTOs.fill(formaPagamento);
                formaPagamentoList.add(formaPagamento);
            }
        }

        if (!this.getCupoms().isEmpty()) {
            for (CupomDTO cupomDTO : this.getCupoms()) {
                Cupom cupom = new Cupom();
                cupomDTO.fill(cupom, cliente);
                cupomList.add(cupom);
            }
        }

        if(dominio.getId() == null){
            String numero = String.format("%04d", new Random().nextInt(10000));
            statusVenda = StatusVenda.EM_PROCESSAMENTO;
        }

        dominio.atualizarDados(this.id, idEndereco, cliente, this.valorTotal, numero, statusVenda, itensPedidos,
                formaPagamentoList, cupomList);
    }

    public static VendaDTO from(Venda venda) {
        VendaDTO dto = new VendaDTO();
        dto.id = venda.getId();
        dto.idEndereco = venda.getIdEndereco();
        dto.idCliente = venda.getCliente().getId();
        dto.valorTotal = venda.getValorTotal();
        dto.numero = venda.getNumero();
        dto.status = venda.getStatusVenda();
        dto.dataCriacao = venda.getDataCriacao();
        dto.itensPedido = venda.getItensPedidos().stream().map(ItensPedidoDTO::from).collect(Collectors.toList());
        dto.formasPagamento = venda.getFormaPagamentoList().stream().map(FormaPagamentoDTO::from).collect(Collectors.toList());
        dto.cupoms = venda.getCupoms().stream().map(CupomDTO::from).collect(Collectors.toList());
        return dto;
    }
}
