package com.fatec.livrariaecommerce.controllers;

import com.fatec.livrariaecommerce.facade.IFacade;
import com.fatec.livrariaecommerce.models.domain.ItensPedido;
import com.fatec.livrariaecommerce.models.domain.Resultado;
import com.fatec.livrariaecommerce.models.domain.StatusPedido;
import com.fatec.livrariaecommerce.models.dto.ItensPedidoDTO;
import com.fatec.livrariaecommerce.models.utils.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor

@CrossOrigin
@RestController
@RequestMapping("/itenspedidos")
public class ItensPedidoController {

    private final IFacade facade;

    // ***********************************************************************

    @PutMapping(path = "/solicitartroca/{idItemPedido}")
    public ResponseEntity<Message> solicitarTrocaItensPedido(@PathVariable int idItemPedido) {
        try {
            ItensPedido itensPedido = new ItensPedido();
            itensPedido.setId(idItemPedido);
            itensPedido = (ItensPedido) this.facade.consultar(itensPedido).getEntidades().get(0);

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
}
