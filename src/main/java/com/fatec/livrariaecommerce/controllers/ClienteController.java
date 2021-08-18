package com.fatec.livrariaecommerce.controllers;

import com.fatec.livrariaecommerce.facade.ClientesFacade;
import com.fatec.livrariaecommerce.models.domain.Cliente;
import com.fatec.livrariaecommerce.models.domain.EntidadeDominio;
import com.fatec.livrariaecommerce.models.domain.Resultado;
import com.fatec.livrariaecommerce.models.dto.ClienteDTO;
import com.fatec.livrariaecommerce.models.dto.TelefoneDTO;
import com.fatec.livrariaecommerce.models.utils.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/clientes")
public class ClienteController {

    private final ClientesFacade facade;

    @PostMapping
    public ResponseEntity<Message> salvarCliente(
            @RequestBody ClienteDTO clienteDto) {
        try {
            Cliente cliente = new Cliente();
            clienteDto.fill(cliente);
            Resultado resultado = this.facade.salvar(cliente);
            Message message = new Message();

            if (resultado.getMensagem() == null) {
                message.setTitle("Sucesso");
                message.setDescription("Cliente cadastrado com sucesso!");
                return ResponseEntity.ok(message);
            } else {
                message.setTitle("Erro");
                message.setDescription(resultado.getMensagem());
                return ResponseEntity.badRequest().body(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(path = "todosClientes")
    public ResponseEntity<List<ClienteDTO>> obterTodosClientes() {
        try {
            List<Cliente> clienteList = new ArrayList<>();
            Resultado resultado = this.facade.consultarTodosClientes();

            for (EntidadeDominio dominio : resultado.getEntidades()) {
                clienteList.add((Cliente) dominio);
            }
            List<ClienteDTO> clienteDTOList = clienteList.stream().map(ClienteDTO::from).collect(Collectors.toList());
            return ResponseEntity.ok(clienteDTOList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Message> inativarClientePeloId(
            @RequestParam int usuarioID) {
        Message message = new Message();
        try {

            Resultado resultadoCliente = this.facade.findClienteByUsuarioId(usuarioID);
            Cliente cliente = (Cliente) resultadoCliente.getEntidades().get(0);
            Resultado resultado = this.facade.excluir(cliente);
            if (resultado.getMensagem() == null) {
                message.setTitle("Sucesso");
                message.setDescription("Cliente foi inativado com sucesso!");
                return ResponseEntity.ok(message);
            } else {
                message.setTitle("Erro");
                message.setDescription(resultado.getMensagem());
                return ResponseEntity.badRequest().body(message);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            message.setTitle("Erro");
            message.setDescription("Falha ao tentar inativar o cliente");
            return ResponseEntity.badRequest().body(message);
        }
    }

    @PutMapping(value = "/{userID}")
    public ResponseEntity<Message> atualizarClientePeloId(
            @PathVariable int userID, @RequestBody ClienteDTO clienteDto
    ) {
        try {

//            Resultado resultado = this.
//            this.facade.updateById(id, cliente);
//            message.setTitle("Sucesso");
//            message.setDescription("Cliente foi atualizado com sucesso!");
//            return new ResponseEntity<Message>(message, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
//            message.setTitle("Erro");
//            message.setDescription(ex.getMessage());
//            return new ResponseEntity<Message>(message, HttpStatus.ACCEPTED);
        }

        return null;
    }

    @GetMapping(value = "/meus_dados/{id}")
    public ResponseEntity<ClienteDTO> getClienteById(@PathVariable int id) {
        try {
            Resultado resultado = this.facade.
                    findClienteByUsuarioId(id);
            return ResponseEntity.ok(ClienteDTO.from((Cliente) resultado.getEntidades().get(0)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }


    }
}
