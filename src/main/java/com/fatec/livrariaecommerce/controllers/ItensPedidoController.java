package com.fatec.livrariaecommerce.controllers;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fatec.livrariaecommerce.facade.IFacade;
import com.fatec.livrariaecommerce.models.domain.*;
import com.fatec.livrariaecommerce.models.dto.*;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor

@CrossOrigin
@RestController
@RequestMapping("/itenspedidos")
public class ItensPedidoController {

    private final IFacade facade;
    private final Logger logger;

    // ***********************************************************************

    @PutMapping(path = "/solicitartroca/{idItemPedido}")
    public ResponseEntity<Message> solicitarTrocaItensPedido(@PathVariable int idItemPedido,
                                                             @RequestParam int qtdTrocada) {
        try {
            ItensPedido itensPedido = new ItensPedido();
            itensPedido.setId(idItemPedido);
            itensPedido = (ItensPedido) this.facade.consultar(itensPedido).getEntidades().get(0);
            itensPedido.setQuantidadeTrocada(qtdTrocada);

            Resultado resultado = this.facade.alterar(itensPedido);
            Message message = new Message();
            if (resultado.getMensagem() == null) {
                message.setTitle("Sucesso!");
                message.setDescription("Troca solicitada com sucesso, aguarde a resposta do admin!");
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

    @PutMapping(path = "/gerenciarsolicitacaotroca/{idItemPedido}")
    public ResponseEntity<Message> gerenciarSolicitacaoTroca(@PathVariable int idItemPedido,
                                                             @Param("status") StatusPedido status) {
        try {
            ItensPedido itensPedido = new ItensPedido();
            itensPedido.setId(idItemPedido);
            itensPedido = (ItensPedido) this.facade.consultar(itensPedido).getEntidades().get(0);
            itensPedido.setStatusPedido(status);

            Resultado resultado = this.facade.alterar(itensPedido);
            Message message = new Message();
            if (resultado.getMensagem() == null) {
                message.setTitle("Sucesso!");
                message.setDescription("Status alterado com sucesso!");
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
    public ResponseEntity<List<ItensPedidoDTO>> listarStatusItensPedidos(@Param("status") StatusPedido status) {
        try {
            ItensPedido itensPedido = new ItensPedido();
            itensPedido.setAtivo(true);
            itensPedido.setStatusPedido(status);
            List<ItensPedidoDTO> itensPedidoDTOList = this.facade.consultar(itensPedido).getEntidades().stream().map(items -> {
                return ItensPedidoDTO.from((ItensPedido) items);
            }).collect(Collectors.toList());
            return ResponseEntity.ok(itensPedidoDTOList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }


    @GetMapping(path = "faturamentoporperiodo")
    public ResponseEntity<List<LivroFaturamentoMensalDTO>> consultarFaturamentoPeriodo(
//    public ResponseEntity<List<Map<String, LivroFaturamentoMensalDTO>>> consultarFaturamentoPeriodo(
            @RequestHeader("livroFaturamentoParamDTO") String json) {

        try {
            List<LivroFaturamentoMensalDTO> listDto = new ArrayList<>();

            List<Map<String, LivroFaturamentoMensalDTO>> content =
                    new ArrayList<Map<String, LivroFaturamentoMensalDTO>>();

            ObjectMapper objectMapper = new ObjectMapper();
            LivroFaturamentoParamDTO[] jsonObjectData = objectMapper.readValue(json, LivroFaturamentoParamDTO[].class);


            for (LivroFaturamentoParamDTO dto : jsonObjectData) {

                List<LivroFaturamentoMensalDTO> faturamentoPorLivro = new ArrayList<>();

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                LocalDateTime initDate = LocalDateTime.ofInstant(formatter.parse(dto.getDataInicio()).toInstant(),
                        ZoneId.systemDefault());
                LocalDateTime endDate = LocalDateTime.ofInstant(formatter.parse(dto.getDataFim()).toInstant(),
                        ZoneId.systemDefault());

                int i = 0;
                for (LocalDateTime date = initDate; date.isBefore(endDate); date = date.plusMonths(1)) {
                    ItensPedido itensPedido = new ItensPedido();
                    Map<String, LivroFaturamentoMensalDTO> teste = new HashMap<>();

                    LivroFaturamentoMensal faturamento = new LivroFaturamentoMensal();

                    itensPedido.setDataCriacao(LocalDateTime.now().withMonth(date.getMonthValue()).withYear(date.getYear()));
                    itensPedido.setIdLivro(dto.getIdLivro());

                    Resultado resultado = this.facade.consultar(itensPedido);


//                    faturamento.setNomeLivro();

//                    faturamento.setNomeLivro("EAE");
//                    faturamento.setIdLivro(((ItensPedido) resultado.getEntidades().get(0)).getIdLivro());

                    faturamento.setData(ConverterDate.translateMonth(date.getMonth().name()) + "/" + date.getYear());
                    faturamento.setFaturamento(resultado.getEntidades().stream().mapToDouble(itPedido -> {
                        return ((ItensPedido) itPedido).getValorTotal();
                    }).sum());
                    i++;

                    teste.put("idLivro", LivroFaturamentoMensalDTO.from(faturamento));

                    //todo: quando voltar finalizar o map de objetos para retornar
//                    faturamentoPorLivro.add(LivroFaturamentoMensalDTO.from(faturamento));

                    listDto.add(LivroFaturamentoMensalDTO.from(faturamento));
                    content.add(teste);

                }

            }

//            return ResponseEntity.ok(content);
            return ResponseEntity.ok(listDto);


        } catch (JsonMappingException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (JsonGenerationException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(path = "faturamentoporperiodoproduto")
    public ResponseEntity<List<LivroFaturamentoMensalDTO>> consultarFaturamentoPeriodoProduto(
            @RequestHeader("json") String json) {

        try {
            List<LivroFaturamentoMensalDTO> listDto = new ArrayList<>();

            ObjectMapper objectMapper = new ObjectMapper();
            PeriodoLivroFaturamentoDTO jsonObjectData = objectMapper.readValue(json, PeriodoLivroFaturamentoDTO.class);


            for (LivroIdDTO dto : jsonObjectData.getIdsLivros()) {

                List<LivroFaturamentoMensalDTO> faturamentoPorLivro = new ArrayList<>();

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                LocalDateTime initDate = LocalDateTime.ofInstant(formatter.parse(jsonObjectData.getDataInicio()).toInstant(),
                        ZoneId.systemDefault());
                LocalDateTime endDate = LocalDateTime.ofInstant(formatter.parse(jsonObjectData.getDataFim()).toInstant(),
                        ZoneId.systemDefault());

                int i = 0;
                for (LocalDateTime date = initDate; date.isBefore(endDate); date = date.plusMonths(1)) {
                    ItensPedido itensPedido = new ItensPedido();

                    LivroFaturamentoMensal livro = new LivroFaturamentoMensal();

                    itensPedido.setDataCriacao(LocalDateTime.now().withMonth(date.getMonthValue()).withYear(date.getYear()));

                    itensPedido.setIdLivro(dto.getId());

                    Resultado resultado = this.facade.consultar(itensPedido);


//                    faturamento.setNomeLivro();

//                    faturamento.setNomeLivro("EAE");
//                    faturamento.setIdLivro(((ItensPedido) resultado.getEntidades().get(0)).getIdLivro());

                    livro.setData(ConverterDate.translateMonth(date.getMonth().name()) + "/" + date.getYear());

                    livro.setFaturamento(resultado.getEntidades().stream().mapToDouble(itPedido -> {
                        return ((ItensPedido) itPedido).getValorTotal();
                    }).sum());
                    i++;

                    listDto.add(LivroFaturamentoMensalDTO.from(livro));
                }

            }
            return ResponseEntity.ok(listDto);


        } catch (JsonMappingException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (JsonGenerationException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }


}
