package com.fatec.livrariaecommerce.controllers;

import com.fatec.livrariaecommerce.facade.IFacade;
import com.fatec.livrariaecommerce.models.domain.*;
import com.fatec.livrariaecommerce.models.dto.ConsultarGeneroClienteDTO;
import com.fatec.livrariaecommerce.models.dto.FaturamentoMensalDTO;
import com.fatec.livrariaecommerce.models.dto.LivroDTO;
import com.fatec.livrariaecommerce.models.dto.VendaDTO;
import com.fatec.livrariaecommerce.models.utils.ConverterDate;
import com.fatec.livrariaecommerce.models.utils.Message;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor

@CrossOrigin
@RestController
@RequestMapping("/vendas")
public class VendasController {

    private final IFacade facade;
    private final Logger logger;

    // ***********************************************************************

    //TODO: IMPLEMENTAR REGRAS DE NEGÓCIO PARA DEVOLUÇÃO/TROCA DE ITENS_PEDIDO

    @PostMapping
    public ResponseEntity<Message> salvarVenda(@RequestBody VendaDTO vendaDTO) {
        try {
            Venda venda = new Venda();
            vendaDTO.fill(venda);
            Resultado resultado = this.facade.salvar(venda);
            Message message = new Message();
            if (resultado.getMensagem() == null) {
                message.setTitle("Sucesso!");
                message.setDescription("Venda efetuada com sucesso!");
                logger.info("Venda efetuada com sucesso. Dados da venda:" +
                        "\nValor total: " + venda.getValorTotal() +
                        "\nNúmero da venda: " + venda.getNumero() +
                        "\nQuantidade de cartões para pagamento: " + venda.getFormaPagamentoList().size() +
                        "\nCompra efetuada pelo cliente de id: "
                        + venda.getCliente().getId());
                return ResponseEntity.ok(message);
            } else {
                message.setTitle("Erro!");
                message.setDescription(resultado.getMensagem());
                return ResponseEntity.badRequest().body(message);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<VendaDTO>> consultarVendas() {
        try {
            Venda venda = new Venda();
            venda.setAtivo(true);
            List<VendaDTO> vendaDTOList = this.facade.consultar(venda).getEntidades().stream().map(ven -> {
                return VendaDTO.from((Venda) ven);
            }).collect(Collectors.toList());
            return ResponseEntity.ok(vendaDTOList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(path = "{usuarioID}")
    public ResponseEntity<List<VendaDTO>> consultarVendasCliente(@PathVariable int usuarioID) {
        try {
            Usuario usuario = new Usuario();
            usuario.setId(usuarioID);
            Cliente cliente = new Cliente();
            cliente.setUsuario(usuario);
            cliente = (Cliente) this.facade.consultar(cliente).getEntidades().get(0);

            Venda venda = new Venda();
            venda.setCliente(cliente);
            List<VendaDTO> vendaDTOList = this.facade.consultar(venda).getEntidades().stream().map(ven -> {
                return VendaDTO.from((Venda) ven);
            }).collect(Collectors.toList());
            return ResponseEntity.ok(vendaDTOList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(path = "{idVenda}")
    public ResponseEntity<Message> alterarStatusVenda(@PathVariable int idVenda,
                                                      @Param("cancelarPedido") boolean cancelarPedido) {
        try {
            Venda venda = new Venda();
            venda.setId(idVenda);
            venda = (Venda) this.facade.consultar(venda).getEntidades().get(0);
            venda.setCancelarVenda(cancelarPedido);
            Resultado resultado = this.facade.alterar(venda);
            Message message = new Message();
            if (resultado.getMensagem() == null) {
                message.setTitle("Sucesso!");
                message.setDescription("Status alterado com sucesso!");

                logger.info("Status da Venda alterado com sucesso. Dados da venda:" +
                        "\nValor total: " + venda.getValorTotal() +
                        "\nNúmero da venda: " + venda.getNumero() +
                        "\nQuantidade de cartões para pagamento: " + venda.getFormaPagamentoList().size() +
                        "\nCompra efetuada pelo cliente de id: " + venda.getCliente().getId() +
                        "\nStatus atual da venda: " + venda.getStatusVenda());

                return ResponseEntity.ok(message);
            } else {
                message.setTitle("Erro!");
                message.setDescription(resultado.getMensagem());
                return ResponseEntity.badRequest().body(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(path = "faturamentomensal")
    public ResponseEntity<Double> consultarFaturamentoMensal(@RequestParam("month") String month,
                                                             @RequestParam(required = false) String year) {
        try {
            Venda venda = new Venda();
            if (year != null) {
                venda.setDataCriacao(LocalDateTime.now().withMonth(Integer.parseInt(month))
                        .withYear(Integer.parseInt(year)));
            } else {
                venda.setDataCriacao(LocalDateTime.now().withMonth(Integer.parseInt(month)));
            }
            Resultado resultado = this.facade.consultar(venda);
            return ResponseEntity.ok(resultado.getEntidades().stream().mapToDouble(vnd -> {
                return ((Venda) vnd).getValorTotal();
            }).sum());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(path = "faturamentoporperiodo")
    public ResponseEntity<List<FaturamentoMensalDTO>> consultarFaturamentoPeriodo(@RequestParam("dataInicio") String dataInicio,
                                                                                  @RequestParam("dataFim") String dataFim) {
        try {
            List<FaturamentoMensalDTO> faturamentos = new ArrayList<>();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            LocalDateTime initDate = LocalDateTime.ofInstant(formatter.parse(dataInicio).toInstant(),
                    ZoneId.systemDefault());
            LocalDateTime endDate = LocalDateTime.ofInstant(formatter.parse(dataFim).toInstant(),
                    ZoneId.systemDefault());
            for (LocalDateTime date = initDate; date.isBefore(endDate); date = date.plusMonths(1)) {
                Venda venda = new Venda();
                FaturamentoMensal faturamentoMensal = new FaturamentoMensal();
                venda.setDataCriacao(LocalDateTime.now().withMonth(date.getMonthValue()).withYear(date.getYear()));
                Resultado resultado = this.facade.consultar(venda);
                faturamentoMensal.setData(ConverterDate.translateMonth(date.getMonth().name()) + "/" + date.getYear());
                faturamentoMensal.setFaturamento(resultado.getEntidades().stream().mapToDouble(vnd -> {
                    return ((Venda) vnd).getValorTotal();
                }).sum());
                faturamentos.add(FaturamentoMensalDTO.from(faturamentoMensal));
            }
            return ResponseEntity.ok(faturamentos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(path = "vendasporgenero")
    public ResponseEntity<ConsultarGeneroClienteDTO> consultarVendasGeneroCliente() {
        try {
            Venda venda = new Venda();
            Cliente cliente = new Cliente();
            cliente.setGenero("Masculino");
            venda.setCliente(cliente);
            Resultado mascResultado = this.facade.consultar(venda);
            cliente.setGenero("Feminino");
            venda.setCliente(cliente);
            Resultado femResultado = this.facade.consultar(venda);

            ConsultarGeneroCliente consultarGeneroCliente = new ConsultarGeneroCliente();
            consultarGeneroCliente.setFeminino(femResultado.getEntidades().size());
            consultarGeneroCliente.setMasculino(mascResultado.getEntidades().size());

            return ResponseEntity.ok(ConsultarGeneroClienteDTO.from(consultarGeneroCliente));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
