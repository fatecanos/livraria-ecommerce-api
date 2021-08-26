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

            //verificar na regra de negocio senha == null
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

    @GetMapping(path = "/listarTodosClientes")
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

//    @DeleteMapping(path = "desativarContaUsuarioByCliente")
//    public ResponseEntity<Message> inativarClientePeloUsuarioId(
//            @RequestParam int usuarioID) {
//        Message message = new Message();
//        try {
//            Resultado resultadoCliente = this.facade.findClienteByUsuarioId(usuarioID);
//            Cliente cliente = (Cliente) resultadoCliente.getEntidades().get(0);
//            Resultado resultado = this.facade.excluir(cliente);
//            if (resultado.getMensagem() == null) {
//                message.setTitle("Sucesso");
//                message.setDescription("Cliente foi inativado com sucesso!");
//                return ResponseEntity.ok(message);
//            } else {
//                message.setTitle("Erro");
//                message.setDescription(resultado.getMensagem());
//                return ResponseEntity.badRequest().body(message);
//            }
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            message.setTitle("Erro");
//            message.setDescription("Falha ao tentar inativar o cliente");
//            return ResponseEntity.badRequest().body(message);
//        }
//    }

    // ***********************************************************************

    @DeleteMapping(path = "inativarContaClienteByAdmin")
    public ResponseEntity<Message> inativarClientePeloId(
            @RequestParam int clienteID) {
        Message message = new Message();
        try {
            Cliente cliente = new Cliente();
            cliente.setId(clienteID);

            Resultado clienteResultado = this.facade.consultar(cliente);
            cliente = (Cliente) clienteResultado.getEntidades().get(0);

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

    // ***********************************************************************

//    @PostMapping(path = "/reativarContaClienteByAdmin")
//    public ResponseEntity<Message> reativarClientePeloID(
//            @RequestParam int clienteID) {
//        Message message = new Message();
//        try {
//
//            Cliente cliente = new Cliente();
//            cliente.setId(clienteID);
//
//            Resultado clienteResultado = this.facade.consultar(cliente);
//            cliente = (Cliente) clienteResultado.getEntidades().get(0);
//
//            Resultado resultado = this.facade.ativarUsuario(cliente);
//
//            if (resultado.getMensagem() == null) {
//                message.setTitle("Sucesso");
//                message.setDescription("Cliente foi reativado com sucesso!");
//                return ResponseEntity.ok(message);
//            } else {
//                message.setTitle("Erro");
//                message.setDescription(resultado.getMensagem());
//                return ResponseEntity.badRequest().body(message);
//            }
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            message.setTitle("Erro");
//            message.setDescription("Falha ao tentar inativar o cliente");
//            return ResponseEntity.badRequest().body(message);
//        }
//    }

    // ***********************************************************************

//    @PutMapping
//    public ResponseEntity<Message> atualizarClientePeloId(@RequestParam int usuarioID, @RequestBody ClienteDTO clienteDto) {
//        Message message = new Message();
//        try {
//            Cliente cliente = new Cliente();
//            Usuario usuario = new Usuario();
//            Resultado usuarioResultado = this.facade.findUsuarioByID(usuarioID);
//
//            if (usuarioResultado.getEntidades() != null) {
//                usuario = (Usuario) usuarioResultado.getEntidades().get(0);
//            } else {
//                new Exception().printStackTrace();
//            }
//
//            cliente.setUsuario(usuario);
//            clienteDto.fill(cliente);
//            Resultado resultado = this.facade.alterar(cliente);
//
//            if (resultado.getMensagem() == null) {
//                message.setTitle("Sucesso");
//                message.setDescription("Cliente foi atualizado com sucesso!");
//                return ResponseEntity.ok(message);
//            } else {
//                message.setTitle("Erro");
//                message.setDescription(resultado.getMensagem());
//                return ResponseEntity.badRequest().body(message);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            message.setTitle("Erro");
//            message.setDescription("Houve um erro ao tentar atualizar o cliente.");
//            return ResponseEntity.badRequest().body(message);
//        }
//    }

    // ***********************************************************************

//    @GetMapping(value = "/meus_dados/{usuarioID}")
//    public ResponseEntity<ClienteDTO> getClienteById(@PathVariable int usuarioID) {
//        try {
//            Resultado resultado = this.facade.
//                    findClienteByUsuarioId(usuarioID);
//            return ResponseEntity.ok(ClienteDTO.from((Cliente) resultado.getEntidades().get(0)));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.badRequest().build();
//        }
//
//
//    }

    // ***********************************************************************
}
