package com.fatec.livrariaecommerce.controllers;

import com.fatec.livrariaecommerce.domain.*;
import com.fatec.livrariaecommerce.dto.ClienteDTO;
import com.fatec.livrariaecommerce.facade.GestaoClientesFacade;
import com.fatec.livrariaecommerce.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final GestaoClientesFacade facade;

    @Autowired
    public ClienteController(GestaoClientesFacade facade) {
        this.facade = facade;
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<Message> salvarCliente(
            @RequestBody ClienteDTO clienteDto) {

        Message message = new Message();
        ArrayList documentos = new ArrayList<Documento>();

        Cliente cliente = new Cliente(
                    clienteDto.getNome(),
                    clienteDto.getSobrenome(),
                    clienteDto.getDataNascimento()
        );

        Documento documento = new Documento(
                clienteDto.getCpf(),
                new TipoDocumento("CPF", "certidao fiscal")
        );

        Usuario usuario = new Usuario(
                clienteDto.getEmail(),
                clienteDto.getSenha(),
                PerfilUsuario.CLIENTE
        );


        documentos.add(documento);

        cliente.setDocumentos(documentos);
        cliente.setUsuario(usuario);

        this.facade.save(cliente);

        message.setTitle("Sucesso");
        message.setDescription("Cliente gravado com sucesso!");
        return new ResponseEntity<Message>(message, HttpStatus.CREATED);
    }
}
