package com.fatec.livrariaecommerce.controllers;

import com.fatec.livrariaecommerce.facade.IFacade;
import com.fatec.livrariaecommerce.models.domain.*;
import com.fatec.livrariaecommerce.models.dto.EnderecoDTO;
import com.fatec.livrariaecommerce.models.dto.TelefoneDTO;
import com.fatec.livrariaecommerce.models.utils.Message;
import lombok.RequiredArgsConstructor;
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
            return ResponseEntity.ok(telefones);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }


    @DeleteMapping(path = "{idTelefone}")
    public ResponseEntity<Message> excluirTelefone(@PathVariable("idTelefone") int idTelefone) {

        System.out.println("Funciona n ? " + idTelefone);

        try {
            Telefone telefone = new Telefone();
            telefone.setId(idTelefone);
            Message message = new Message();
            Resultado resultado = this.facade.excluir(telefone);

            if (resultado.getMensagem() == null) {
                message.setTitle("Sucesso!");
                message.setDescription("Telefone desativado com sucesso!");
                return ResponseEntity.ok(message);
            } else {
                message.setTitle("Erro!");
                message.setDescription("Ocorreu um erro ao tentar desativar a conta.");
                return ResponseEntity.badRequest().body(message);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

}
