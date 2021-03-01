package com.fatec.livrariaecommerce.controllers;

import com.fatec.livrariaecommerce.domain.*;
import com.fatec.livrariaecommerce.dto.ClienteDTO;
import com.fatec.livrariaecommerce.dto.EnderecoDTO;
import com.fatec.livrariaecommerce.facade.GestaoClientesFacade;
import com.fatec.livrariaecommerce.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final GestaoClientesFacade facade;

    @Autowired
    public ClienteController(GestaoClientesFacade facade) {
        this.facade = facade;
    }

    @CrossOrigin
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
    @GetMapping(produces = "application/json")
    public List<ClienteDTO> obterTodosClientes() {
        List<Cliente> clientes = this.facade.viewAll();
        List<ClienteDTO> clientesDto;

        clientesDto = clientes.stream().map(cliente ->
            ClienteDTO.montar()
                    .id(cliente.getId())
                    .nome(cliente.getNome())
                    .sobrenome(cliente.getSobrenome())
                    .cpf(cliente.getDocumentos().stream()
                            .findFirst().get().getCodigo())
                    .email(cliente.getUsuario().getEmail())
                    .dataNascimento(cliente.getDataNascimento())
                    .enderecos(cliente.getEnderecos().stream().map(endereco ->
                            EnderecoDTO.montar()
                                    .id(endereco.getId())
                                    .logradouro(endereco.getLogradouro())
                                    .bairro(endereco.getBairro())
                                    .cidade(endereco.getCidade())
                                    .tipoEndereco(endereco.getTipoEndereco())
                                    .complemento(endereco.getComplemento())
                                    .numero(endereco.getNumero())
                                    .cep(endereco.getCep())
                                .build()
                        ).collect(Collectors.toList())
                    )
                    .build()
        ).collect(Collectors.toList());

        return clientesDto;
    }

    @CrossOrigin
    @DeleteMapping(produces = "application/json")
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
    @PutMapping(value = "/{id}", produces = "application/json")
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
    @GetMapping(value = "/{id}",produces = "application/json")
    public ClienteDTO getClienteById(@PathVariable int id) {
        Cliente cliente = this.facade.findClienteById(id);

        return ClienteDTO.montar()
            .id(cliente.getId())
            .nome(cliente.getNome())
            .sobrenome(cliente.getSobrenome())
            .cpf(cliente.getDocumentos().stream().findFirst().get().getCodigo())
            .email(cliente.getUsuario().getEmail())
            .dataNascimento(cliente.getDataNascimento())
            .enderecos(cliente.getEnderecos().stream().map(endereco ->
                EnderecoDTO.montar()
                    .id(endereco.getId())
                    .logradouro(endereco.getLogradouro())
                    .bairro(endereco.getBairro())
                    .cidade(endereco.getCidade())
                    .tipoEndereco(endereco.getTipoEndereco())
                    .complemento(endereco.getComplemento())
                    .numero(endereco.getNumero())
                    .cep(endereco.getCep())
                .build()
            ).collect(Collectors.toList())
        ).build();
    }
}
