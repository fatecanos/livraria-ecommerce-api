package com.fatec.livrariaecommerce.controllers;

import com.fatec.livrariaecommerce.facade.IFacade;
import com.fatec.livrariaecommerce.models.domain.Cliente;
import com.fatec.livrariaecommerce.models.domain.Notificacao;
import com.fatec.livrariaecommerce.models.domain.Resultado;
import com.fatec.livrariaecommerce.models.domain.Usuario;
import com.fatec.livrariaecommerce.models.dto.NotificacaoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/notificacao")
public class NotificacaoController {

    private final IFacade facade;

    @GetMapping(path = "/{idUsuario}")
    public ResponseEntity<List<NotificacaoDTO>> consultarNotificacoes(@PathVariable("idUsuario") int idUsuario) {

        try {
            Notificacao notificacao = new Notificacao();
            Usuario usuario = new Usuario();
            usuario.setId(idUsuario);

            Cliente cliente = new Cliente();
            cliente.setUsuario(usuario);
            cliente = (Cliente) this.facade.consultar(cliente).getEntidades().get(0);
            notificacao.setCliente(cliente);
            Resultado resultado = this.facade.consultar(notificacao);

            List<NotificacaoDTO> notificacoes = this.facade.consultar(notificacao).getEntidades().stream().map(notify -> {
                return NotificacaoDTO.from((Notificacao) notify);

            }).collect(Collectors.toList());
            return ResponseEntity.ok(notificacoes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

}
