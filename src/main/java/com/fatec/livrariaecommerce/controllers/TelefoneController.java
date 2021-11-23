package com.fatec.livrariaecommerce.controllers;

import com.fatec.livrariaecommerce.facade.IFacade;
import com.fatec.livrariaecommerce.models.domain.*;
import com.fatec.livrariaecommerce.models.dto.TelefoneDTO;
import com.fatec.livrariaecommerce.models.utils.Message;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/telefone")
public class TelefoneController {

    private final IFacade facade;
    private final Logger logger;

    @PostMapping(path = "{usuarioID}")
    public ResponseEntity<Message> salvarTelefone(@PathVariable int usuarioID, @RequestBody TelefoneDTO telefoneDTO) {
        try {

            Usuario usuario = new Usuario();
            usuario.setId(usuarioID);

            Cliente cliente = new Cliente();
            cliente.setUsuario(usuario);
            cliente = (Cliente) this.facade.consultar(cliente).getEntidades().get(0);

            Telefone telefone = new Telefone(cliente);

            telefoneDTO.fill(telefone);

            Resultado resultado = this.facade.salvar(telefone);
            Message message = new Message();
            if (resultado.getMensagem() == null) {
                message.setTitle("Sucesso!");
                message.setDescription("Telefone cadastrado com sucesso!");
                logger.info("Telefone cadastrado com sucesso. Dados do telefone:" +
                        "\nID telefone: " + telefone.getId() +
                        "\nNum. Telefone: " + telefone.getTelefone() +
                        "\nTelefone salvo no cliente: " + cliente.getId());
                return ResponseEntity.ok(message);
            } else {
                message.setTitle("Erro!");
                message.setDescription("Erro ao cadastrar telefone!");
                return ResponseEntity.badRequest().body(message);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping(path = "{usuarioID}")
    public ResponseEntity<Message> alterarTelefone(@PathVariable int usuarioID, @RequestBody TelefoneDTO telefoneDTO) {
        try {

            Usuario usuario = new Usuario();
            usuario.setId(usuarioID);

            Cliente cliente = new Cliente();
            cliente.setUsuario(usuario);
            cliente = (Cliente) this.facade.consultar(cliente).getEntidades().get(0);

            Telefone telefone = new Telefone(cliente);

            telefoneDTO.fill(telefone);

            Resultado resultado = this.facade.alterar(telefone);

            Message message = new Message();
            if (resultado.getMensagem() == null) {
                message.setTitle("Sucesso!");
                message.setDescription("Telefone alterado com sucesso!");
                logger.info("Telefone alterado com sucesso. Dados do telefone:" +
                        "\nID telefone: " + telefone.getId() +
                        "\nTelefone anterior: " + telefoneDTO.getTelefone() +
                        "\nNovo telefone: " + ((Telefone) resultado.getEntidades().get(0)).getTelefone());
                return ResponseEntity.ok(message);
            } else {
                message.setTitle("Erro!");
                message.setDescription("Erro ao alterar telefone!");
                return ResponseEntity.badRequest().body(message);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping(path = "/{idUsuario}")
    public ResponseEntity<List<TelefoneDTO>> consultarTelefones(@PathVariable("idUsuario") int idUsuario) {

        try {
            Telefone telefone = new Telefone();
            Usuario usuario = new Usuario();
            usuario.setId(idUsuario);

            Cliente cliente = new Cliente();
            cliente.setUsuario(usuario);
            cliente = (Cliente) this.facade.consultar(cliente).getEntidades().get(0);
            telefone.setCliente(cliente);
            List<TelefoneDTO> telefones = this.facade.consultar(telefone).getEntidades().stream().map(tel -> {
                return TelefoneDTO.from((Telefone) tel);
            }).collect(Collectors.toList());
            logger.info("Ola, da controller do telfone");
            return ResponseEntity.ok(telefones);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }


    @DeleteMapping(path = "{idTelefone}")
    public ResponseEntity<Message> excluirTelefone(@PathVariable("idTelefone") int idTelefone) {
        try {
            Telefone telefone = new Telefone();
            telefone.setId(idTelefone);
            Message message = new Message();
            Resultado resultado = this.facade.excluir(telefone);

            if (resultado.getMensagem() == null) {
                message.setTitle("Sucesso!");
                message.setDescription("Telefone desativado com sucesso!");
                logger.info("Telefone excluido com sucesso. Dados do telefone:" +
                        "\nID telefone: " + telefone.getId());
                return ResponseEntity.ok(message);
            } else {
                message.setTitle("Erro!");
                message.setDescription("Ocorreu um erro ao tentar desativar o telefone.");
                return ResponseEntity.badRequest().body(message);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

}
