package com.fatec.livrariaecommerce.controllers;

import com.fatec.livrariaecommerce.facade.IFacade;
import com.fatec.livrariaecommerce.models.domain.*;
import com.fatec.livrariaecommerce.models.dto.CartaoCreditoDTO;
import com.fatec.livrariaecommerce.models.utils.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/cartoes")
public class CartaoController {

    private final IFacade facade;

    @PostMapping(path = "{idUsuario}")
    public ResponseEntity<CartaoCreditoDTO> salvarCartao(@PathVariable int idUsuario, @RequestBody CartaoCreditoDTO cartaoCreditoDTO) {
        try {
            Usuario usuario = new Usuario();
            usuario.setId(idUsuario);

            Cliente cliente = new Cliente();
            cliente.setUsuario(usuario);
            cliente = (Cliente) this.facade.consultar(cliente).getEntidades().get(0);

            CartaoCredito cartaoCredito = new CartaoCredito(cliente);
            cartaoCreditoDTO.fill(cartaoCredito);

            Resultado resultado = this.facade.salvar(cartaoCredito);

            if (resultado.getMensagem() == null) {
                return ResponseEntity.ok(CartaoCreditoDTO.from((CartaoCredito) resultado.getEntidades().get(0)));
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(path = "{idUsuario}")
    public ResponseEntity<Message> alterarCartao(@PathVariable int idUsuario,
                                                 @RequestBody CartaoCreditoDTO cartaoCreditoDTO) {
        try {
            Message message = new Message();
            Endereco endereco = new Endereco();
            Usuario usuario = new Usuario();
            usuario.setId(idUsuario);

            Cliente cliente = new Cliente();
            cliente.setUsuario(usuario);
            cliente = (Cliente) this.facade.consultar(cliente).getEntidades().get(0);

            CartaoCredito cartaoCredito = new CartaoCredito(cliente);
            cartaoCreditoDTO.fill(cartaoCredito);

            Resultado resultado = this.facade.alterar(cartaoCredito);

            if (resultado.getMensagem() == null) {
                message.setTitle("Sucesso!");
                message.setDescription("Cartão atualizado com sucesso!");
                return ResponseEntity.ok(message);

            } else {
                message.setTitle("Erro");
                message.setDescription("Erro ao atualizar o cartão.");
                return ResponseEntity.badRequest().body(message);

            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Message> excluirCartao() {
        return null;
    }

    @GetMapping(path = "/{idUsuario}")
    public ResponseEntity<List<CartaoCreditoDTO>> consultarCartoes(@PathVariable("idUsuario") int idUsuario) {
        try {
            CartaoCredito cartaoCredito = new CartaoCredito();
            Usuario usuario = new Usuario();
            usuario.setId(idUsuario);

            Cliente cliente = new Cliente();
            cliente.setUsuario(usuario);
            cliente = (Cliente) this.facade.consultar(cliente).getEntidades().get(0);
            cartaoCredito.setCliente(cliente);
            List<CartaoCreditoDTO> cartoes = this.facade.consultar(cartaoCredito).getEntidades().stream().map(card -> {
                return CartaoCreditoDTO.from((CartaoCredito) card);
            }).collect(Collectors.toList());
            return ResponseEntity.ok(cartoes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

}
