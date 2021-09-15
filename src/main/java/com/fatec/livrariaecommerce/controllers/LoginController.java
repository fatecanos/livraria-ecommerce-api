package com.fatec.livrariaecommerce.controllers;

import com.fatec.livrariaecommerce.facade.IFacade;
import com.fatec.livrariaecommerce.models.domain.Resultado;
import com.fatec.livrariaecommerce.models.domain.Usuario;
import com.fatec.livrariaecommerce.models.dto.LoginDTO;
import com.fatec.livrariaecommerce.models.dto.UsuarioDTO;
import com.fatec.livrariaecommerce.models.utils.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {

    private final IFacade facade;

    @PostMapping
    public ResponseEntity<UsuarioDTO> login(@RequestBody LoginDTO loginDTO) {
        try {
            Usuario usuario = new Usuario();
            loginDTO.fill(usuario);
            Resultado resultado = this.facade
                    .consultar(usuario);
            if (resultado.getMensagem() == null) {
                return ResponseEntity.ok(new UsuarioDTO((Usuario) resultado.getEntidades().get(0)));
            } else {
                System.out.println(resultado.getMensagem());
                new Exception().printStackTrace();
                return ResponseEntity.badRequest().build();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(path = {"/{idUsuario}"})
    public ResponseEntity<Message> alterarUsuario(@PathVariable int idUsuario, @RequestBody LoginDTO loginDTO) {
        Message message = new Message();
        try {
            Usuario usuario = new Usuario();
            usuario.setId(idUsuario);
            usuario.setEmail(loginDTO.getEmail());
            usuario.setSenha(loginDTO.getSenha());

            Resultado resultado = this.facade
                    .alterar(usuario);

            if (resultado.getMensagem() == null) {
                message.setTitle("Sucesso!");
                message.setDescription("Email alterado com sucesso!");
                return ResponseEntity.ok(message);
            } else {
                message.setTitle("Erro!");
                message.setDescription("Erro ao alterar email");
                return ResponseEntity.badRequest().body(message);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/logout")
    public ResponseEntity logout(HttpSession session) {
        return ResponseEntity.ok().build();
    }

}
