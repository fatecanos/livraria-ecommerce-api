package com.fatec.livrariaecommerce.controllers;

import com.fatec.livrariaecommerce.dao.UsuarioDao;
import com.fatec.livrariaecommerce.facade.LoginFacade;
import com.fatec.livrariaecommerce.models.domain.Usuario;
import com.fatec.livrariaecommerce.models.dto.ClienteDTO;
import com.fatec.livrariaecommerce.models.dto.LoginDTO;
import com.fatec.livrariaecommerce.models.dto.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginFacade loginFacade;

    @PostMapping
    public ResponseEntity<UsuarioDTO> login(@RequestBody LoginDTO loginDTO, HttpSession session) {

        try {
            Usuario usuario = this.loginFacade
                    .findByEmailAndSenha(loginDTO.getEmail(), loginDTO.getSenha())
                    .orElseThrow(Exception::new);

            session.setAttribute("loggedUserId", usuario.getId());

            System.out.println("Olha quem logou: " + session.getAttribute("loggedUserId"));

            return ResponseEntity.ok(new UsuarioDTO(usuario));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/logout")
    public ResponseEntity logout(HttpSession session) {
        session.setAttribute("loggedUserId", null);
        return ResponseEntity.ok().build();
    }

}
