package com.fatec.livrariaecommerce.controllers;

import com.fatec.livrariaecommerce.facade.IFacade;
import com.fatec.livrariaecommerce.models.domain.*;
import com.fatec.livrariaecommerce.models.dto.ClienteDTO;
import com.fatec.livrariaecommerce.models.utils.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/clientes")
public class ClienteController {

    private final IFacade facade;

    // ***********************************************************************

    @PostMapping
    public ResponseEntity<Message> salvarCliente(
            @RequestBody ClienteDTO clienteDto) {
        try {
            Message message = new Message();
            Usuario usuario = new Usuario();
            usuario.setEmail(clienteDto.getEmail());
            usuario.setSenha(clienteDto.getSenha());

            Resultado usuarioResultado = this.facade.consultar(usuario);
            if (!usuarioResultado.getEntidades().isEmpty()) {
                message.setTitle("Erro");
                message.setDescription("Este email já está cadastrado");
                return ResponseEntity.badRequest().body(message);
            }

            Cliente cliente = new Cliente();
            clienteDto.fill(cliente);
            Resultado clienteResultado = this.facade.salvar(cliente);

            if (clienteResultado.getMensagem() == null) {
                message.setTitle("Sucesso");
                message.setDescription("Cliente cadastrado com sucesso!");
                return ResponseEntity.ok(message);
            } else {
                message.setTitle("Erro");
                message.setDescription(clienteResultado.getMensagem());
                return ResponseEntity.badRequest().body(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    // ***********************************************************************

    @PutMapping
    public ResponseEntity<Message> alterarCliente(@RequestParam int idUsuario, @RequestBody ClienteDTO clienteDto) {
        Message message = new Message();
        try {
            Cliente cliente = new Cliente();
            Usuario usuario = new Usuario();
            usuario.setId(idUsuario);

            Resultado usuarioResultado = this.facade.consultar(usuario);
            usuario = (Usuario) usuarioResultado.getEntidades().get(0);

            cliente.setUsuario(usuario);
            clienteDto.fill(cliente);
            Resultado clienteResultado = this.facade.alterar(cliente);

            if (clienteResultado.getMensagem() == null) {
                message.setTitle("Sucesso");
                message.setDescription("Cliente alterado com sucesso!");
                return ResponseEntity.ok(message);
            } else {
                message.setTitle("Erro");
                message.setDescription(clienteResultado.getMensagem());
                return ResponseEntity.badRequest().body(message);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            message.setTitle("Erro");
            message.setDescription("Houve um erro ao tentar atualizar o cliente.");
            return ResponseEntity.badRequest().body(message);
        }
    }


    // ***********************************************************************

    @GetMapping(value = "/meusdados/{usuarioID}")
    public ResponseEntity<ClienteDTO> consultarClientePeloID(@PathVariable int usuarioID) {
        try {
            Usuario usuario = new Usuario();
            usuario.setId(usuarioID);
            Cliente cliente = new Cliente();
            cliente.setUsuario(usuario);
            cliente = (Cliente) this.facade.consultar(cliente).getEntidades().get(0);

            return ResponseEntity.ok(ClienteDTO.from(cliente));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    // ***********************************************************************

    @GetMapping(path = "/listarclientes")
    public ResponseEntity<List<ClienteDTO>> obterTodosClientes(
            @RequestParam(value = "filtro", defaultValue = "") String filtro) {
        try {
            Cliente cliente = new Cliente();
            //incluir no filtro de params
//            cliente.setAtivo(true);
            cliente.setNome(filtro);
            cliente.setSobrenome(filtro);
            cliente.setCpf(filtro);
            List<ClienteDTO> clientes = this.facade.consultar(cliente).getEntidades().stream().map(cli -> {
                return ClienteDTO.from((Cliente) cli);
            }).collect(Collectors.toList());
            return ResponseEntity.ok(clientes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    // ***********************************************************************

    @DeleteMapping(path = "inativarcontabyuser")
    public ResponseEntity<Message> inativarConta(
            @RequestParam int idUsuario) {
        Message message = new Message();
        try {
            Usuario usuario = new Usuario();
            usuario.setId(idUsuario);
            Cliente cliente = new Cliente();
            cliente.setUsuario(usuario);
            Resultado clienteResultado = this.facade.consultar(cliente);
            cliente = (Cliente) clienteResultado.getEntidades().get(0);

            Resultado resultado = this.facade.excluir(cliente);
            Resultado usuarioResultado = this.facade.excluir(usuario);
            if (resultado.getMensagem() == null && usuarioResultado.getMensagem() == null) {
                message.setTitle("Sucesso!");
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

    // ***********************************************************************

    @DeleteMapping(path = "inativarcontabyadmin")
    public ResponseEntity<Message> inativarContaByClienteId(
            @RequestParam int idCliente) {
        Message message = new Message();
        try {
            Usuario usuario = new Usuario();
            Cliente cliente = new Cliente();
            cliente.setId(idCliente);

            Resultado clienteResultado = this.facade.consultar(cliente);
            Cliente usuarioCliente = (Cliente) clienteResultado.getEntidades().get(0);

            System.out.println("Ué carai: " + usuarioCliente.getCpf());
            System.out.println("Ué carai: " + usuarioCliente.getUsuario().getId());

            usuario.setId(usuarioCliente.getUsuario().getId());

            Resultado resultado = this.facade.excluir(cliente);
            Resultado usuarioResultado = this.facade.excluir(usuario);
            if (resultado.getMensagem() == null && usuarioResultado.getMensagem() == null) {
                message.setTitle("Sucesso!");
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

    // ***********************************************************************

    @PutMapping(path = "/reativarconta")
    public ResponseEntity<Message> reativarClientePeloID(
            @RequestParam int idCliente) {
        Message message = new Message();
        try {
            Usuario usuario;
            Cliente cliente = new Cliente();
            cliente.setId(idCliente);

            Resultado clienteResultado = this.facade.consultar(cliente);
            cliente = (Cliente) clienteResultado.getEntidades().get(0);
            usuario = cliente.getUsuario();
            if (cliente.getAtivo()) {
                message.setTitle("Erro");
                message.setDescription("Erro ao ativar conta. A conta já encontra-se ativa!");
                return ResponseEntity.badRequest().body(message);
            }

            System.out.println("Ta aiivo ai carai? " + cliente.getAtivo());

            cliente.setAtivo(true);
            usuario.setAtivo(true);
            cliente.setUsuario(usuario);

//            TODO: NULL ERROR IN ALTERAR METHOD
            Resultado resultado = this.facade.alterar(cliente);
            if (resultado.getMensagem() == null) {
                message.setTitle("Sucesso");
                message.setDescription("Cliente foi reativado com sucesso!");
                return ResponseEntity.ok(message);
            } else {
                message.setTitle("Erro");
                message.setDescription(resultado.getMensagem());
                return ResponseEntity.badRequest().body(message);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            message.setTitle("Erro");
            message.setDescription("Falha ao tentar ativar a conta do cliente");
            return ResponseEntity.badRequest().body(message);
        }
    }

    // ***********************************************************************

}
