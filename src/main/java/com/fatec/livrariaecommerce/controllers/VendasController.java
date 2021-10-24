package com.fatec.livrariaecommerce.controllers;

import com.fatec.livrariaecommerce.facade.IFacade;
import com.fatec.livrariaecommerce.models.domain.*;
import com.fatec.livrariaecommerce.models.dto.LivroDTO;
import com.fatec.livrariaecommerce.models.dto.VendaDTO;
import com.fatec.livrariaecommerce.models.utils.Message;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
}
