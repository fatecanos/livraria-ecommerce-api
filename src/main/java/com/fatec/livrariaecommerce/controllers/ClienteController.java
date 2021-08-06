package com.fatec.livrariaecommerce.controllers;

import com.fatec.livrariaecommerce.models.domain.*;
import com.fatec.livrariaecommerce.models.dto.ClienteDTO;
import com.fatec.livrariaecommerce.models.dto.EnderecoDTO;
import com.fatec.livrariaecommerce.facade.GestaoClientesFacade;
import com.fatec.livrariaecommerce.models.utils.Message;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final GestaoClientesFacade facade;

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Message> salvarCliente(
            @RequestBody ClienteDTO clienteDto, HttpSession session) {

//        System.out.println("Usuario logado: " + session.getAttribute("loggedUserId"));
//
//        if (session.getAttribute("loggedUserId") == null) {
//            return ResponseEntity.badRequest().build();
//        }

        Message message = new Message();
        List documentos = new ArrayList<Documento>();

        Cliente cliente = new Cliente(
                clienteDto.getNome(),
                clienteDto.getSobrenome(),
                clienteDto.getDataNascimento()
        );

        Documento documento = new Documento(
                clienteDto.getCpf(),
                new TipoDocumento(
                        "cadastro de pessoa física",
                        "CPF")
        );

        documentos.add(documento);

        Usuario usuario = new Usuario(
                clienteDto.getEmail(),
                clienteDto.getSenha(),
                PerfilUsuario.CLIENTE
        );

        cliente.setDocumentos(documentos);
        cliente.setUsuario(usuario);

        try {
            this.facade.save(cliente);
            message.setTitle("Sucesso");
            message.setDescription("Cliente gravado com sucesso!");
            return new ResponseEntity<Message>(message, HttpStatus.CREATED);
        } catch (IllegalStateException ex) {
            message.setTitle("Erro");
            message.setDescription(ex.getMessage());
            return new ResponseEntity<Message>(message, HttpStatus.ACCEPTED);
        }
    }

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> obterTodosClientes() {
        try {
            List<Cliente> clientes = this.facade.viewAll();
            List<ClienteDTO> clienteDTOList = new ArrayList<>();
            for (Cliente cliente : clientes) {
                clienteDTOList.add(new ClienteDTO(cliente));
            }
            return ResponseEntity.ok(clienteDTOList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    //TODO: RETURN CROSS ORIGIN TO RESOLVE FRONT PROBLEM
    @CrossOrigin
    @DeleteMapping
    public ResponseEntity<Message> inativarClientePeloId(
            @RequestParam int id) {
        Message message = new Message();
        try {
            this.facade.disableById(id);
            message.setTitle("Sucesso");
            message.setDescription("Cliente foi inativado com sucesso!");
            return new ResponseEntity<Message>(message, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            message.setTitle("Erro");
            message.setDescription(ex.getMessage());
            return new ResponseEntity<Message>(message, HttpStatus.ACCEPTED);
        }
    }

    @CrossOrigin
    @PutMapping(value = "/{id}")
    public ResponseEntity<Message> atualizarClientePeloId(
            @PathVariable int id, @RequestBody ClienteDTO clienteDto
    ) {
        Message message = new Message();
        ArrayList documentos = new ArrayList<Documento>();

        Cliente cliente = new Cliente(
                clienteDto.getNome(),
                clienteDto.getSobrenome(),
                clienteDto.getDataNascimento()
        );
        Documento documento = new Documento(
                clienteDto.getCpf(),
                new TipoDocumento(
                        "cadastro de pessoa física",
                        "CPF")
        );

        documentos.add(documento);

        Usuario usuario = new Usuario(
                clienteDto.getEmail(),
                clienteDto.getSenha(),
                PerfilUsuario.CLIENTE
        );

        cliente.setDocumentos(documentos);
        cliente.setUsuario(usuario);

        try {
            this.facade.updateById(id, cliente);
            message.setTitle("Sucesso");
            message.setDescription("Cliente foi atualizado com sucesso!");
            return new ResponseEntity<Message>(message, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            message.setTitle("Erro");
            message.setDescription(ex.getMessage());
            return new ResponseEntity<Message>(message, HttpStatus.ACCEPTED);
        }
    }

    @CrossOrigin
    @GetMapping(path = "/meus_dados")
    public ResponseEntity<ClienteDTO> getClienteById(HttpSession session) {
        int id;
        System.out.println("Usuario logado: " + session.getAttribute("loggedUserId"));
        if (session.getAttribute("loggedUserId") == null) {
            return ResponseEntity.badRequest().build();
        }

        id = Integer.parseInt(session.getAttribute("loggedUserId").toString());


        try {
            return ResponseEntity.ok(new ClienteDTO(this.facade.findClienteById(id)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
