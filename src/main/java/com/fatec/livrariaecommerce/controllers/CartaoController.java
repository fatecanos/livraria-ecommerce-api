package com.fatec.livrariaecommerce.controllers;

import com.fatec.livrariaecommerce.facade.IFacade;
import com.fatec.livrariaecommerce.models.domain.*;
import com.fatec.livrariaecommerce.models.dto.CartaoCreditoDTO;
import com.fatec.livrariaecommerce.models.utils.Message;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/cartoes")
public class CartaoController {

    private final IFacade facade;
    private final Logger logger;

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
                logger.info("Cartão cadastrado com sucesso:" +
                        "\nBandeira: " + ((CartaoCredito) resultado.getEntidades().get(0)).getBandeira() +
                        "\nCódigo de segurança: " + ((CartaoCredito) resultado.getEntidades().get(0)).getCodigoSeguranca() +
                        "\nNome impresso: " + ((CartaoCredito) resultado.getEntidades().get(0)).getNomeImpressoCartao() +
                        "\nNumero: " + ((CartaoCredito) resultado.getEntidades().get(0)).getNumeroCartao() +
                        "\nSalvo no cadastro do cliente de id: " + cliente.getId());
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

    @DeleteMapping(path = "{idCartao}")
    public ResponseEntity<Message> excluirCartao(@PathVariable("idCartao") int idCartao) {

        try {
            CartaoCredito cartaoCredito = new CartaoCredito();
            cartaoCredito.setId(idCartao);
            Message message = new Message();
            Resultado resultado = this.facade.excluir(cartaoCredito);
            if (resultado.getMensagem() == null) {
                message.setTitle("Sucesso!");
                message.setDescription("Cartão de crédito desativado com sucesso!");
                logger.info("Cartão excluido com sucesso:" +
                        "\nCartão de id: " + idCartao);
                return ResponseEntity.ok(message);
            } else {
                message.setTitle("Erro!");
                message.setDescription("Ocorreu um erro ao tentar desativar o cartão de crédito.");
                return ResponseEntity.badRequest().body(message);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(path = "/{idUsuario}")
    public ResponseEntity<List<CartaoCreditoDTO>> consultarCartoes(@PathVariable("idUsuario") int idUsuario) {
        try {
            CartaoCredito cartaoCredito = new CartaoCredito();
            Usuario usuario = new Usuario();
            usuario.setId(idUsuario);
            List<CartaoCreditoDTO> cartoes = new ArrayList<>();
            Cliente cliente = new Cliente();
            cliente.setUsuario(usuario);
            cliente = (Cliente) this.facade.consultar(cliente).getEntidades().get(0);
            cartaoCredito.setCliente(cliente);
            Resultado resultado = this.facade.consultar(cartaoCredito);
            for (EntidadeDominio dominio : resultado.getEntidades()) {
                CartaoCredito cc = (CartaoCredito) dominio;
                if (cc.getAtivo()) {
                    cartoes.add(CartaoCreditoDTO.from(cc));
                }
            }
            return ResponseEntity.ok(cartoes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

}
