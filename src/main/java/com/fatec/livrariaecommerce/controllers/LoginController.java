package com.fatec.livrariaecommerce.controllers;

import com.fatec.livrariaecommerce.dao.UsuarioDao;
import com.fatec.livrariaecommerce.facade.UsuarioFacade;
import com.fatec.livrariaecommerce.models.domain.Resultado;
import com.fatec.livrariaecommerce.models.domain.Usuario;
import com.fatec.livrariaecommerce.models.dto.ClienteDTO;
import com.fatec.livrariaecommerce.models.dto.LoginDTO;
import com.fatec.livrariaecommerce.models.dto.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {

    private final UsuarioFacade usuarioFacade;

//    @PostMapping
//    public ResponseEntity<UsuarioDTO> login(@RequestBody LoginDTO loginDTO) {
//
//
//        try {
//            Usuario usuario = new Usuario();
//            loginDTO.fill(usuario);
//
//            Resultado resultado = this.usuarioFacade
//                    .findByEmailAndSenha(usuario);
//
//            if (resultado.getMensagem() == null) {
//                return ResponseEntity.ok(new UsuarioDTO((Usuario) resultado.getEntidades().get(0)));
//            } else {
//                System.out.println(resultado.getMensagem());
//                new Exception().printStackTrace();
//                return ResponseEntity.badRequest().build();
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.notFound().build();
//        }
//    }

    @GetMapping("/logout")
    public ResponseEntity logout(HttpSession session) {
        session.setAttribute("loggedUserId", null);
        return ResponseEntity.ok().build();
    }

}
