package com.fatec.livrariaecommerce.controllers;

import com.fatec.livrariaecommerce.facade.ClientesFacade;
import com.fatec.livrariaecommerce.facade.UsuarioFacade;
import com.fatec.livrariaecommerce.models.domain.Cliente;
import com.fatec.livrariaecommerce.models.domain.PerfilUsuario;
import com.fatec.livrariaecommerce.models.domain.Resultado;
import com.fatec.livrariaecommerce.models.domain.Usuario;
import com.fatec.livrariaecommerce.models.dto.ClienteDTO;
import com.fatec.livrariaecommerce.models.utils.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

            Resultado resultadoCliente = this.facade.salvar(cliente);

            System.out.println("SALVOU ESSa CARAIA");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
        return null;
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> obterTodosClientes() {
        try {
//            List<Cliente> clientes = this.facade.viewAll();
            List<ClienteDTO> clienteDTOList = new ArrayList<>();
//            for (Cliente cliente : clientes) {
//                clienteDTOList.add(new ClienteDTO(cliente));
//            }
            return ResponseEntity.ok(clienteDTOList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

//    @DeleteMapping
//    public ResponseEntity<Message> inativarClientePeloId(
//            @RequestParam int id) {
//        Message message = new Message();
//        try {
//            this.gestaoClientesFacade.disableById(id);
//            message.setTitle("Sucesso");
//            message.setDescription("Cliente foi inativado com sucesso!");
//            return new ResponseEntity<Message>(message, HttpStatus.ACCEPTED);
//        } catch (Exception ex) {
//            message.setTitle("Erro");
//            message.setDescription(ex.getMessage());
//            return new ResponseEntity<Message>(message, HttpStatus.ACCEPTED);
//        }
//    }

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

//    @GetMapping(value = "/meus_dados/{id}")
//    public ResponseEntity<ClienteDTO> getClienteById(@PathVariable int id) {
//        try {
//            Cliente cliente = this.gestaoClientesFacade.
//                    findClienteByUsuarioId(id)
//                    .orElseThrow(Exception::new);
//            return ResponseEntity.ok(new ClienteDTO(cliente));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.badRequest().build();
//        }
//
//
//    }
}
