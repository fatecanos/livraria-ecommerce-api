package com.fatec.livrariaecommerce.controllers;

import com.fatec.livrariaecommerce.facade.IFacade;
import com.fatec.livrariaecommerce.models.domain.Cliente;
import com.fatec.livrariaecommerce.models.domain.Resultado;
import com.fatec.livrariaecommerce.models.domain.Telefone;
import com.fatec.livrariaecommerce.models.domain.Usuario;
import com.fatec.livrariaecommerce.models.dto.TelefoneDTO;
import com.fatec.livrariaecommerce.models.utils.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/telefone")
public class TelefoneController {

    private final IFacade facade;

    @PostMapping(path = "{usuarioID}")
    public ResponseEntity<Message> salvarTelefone(@PathVariable int usuarioID, @RequestBody TelefoneDTO telefoneDTO) {
        try {

            Usuario usuario = new Usuario();
            usuario.setId(usuarioID);

            Cliente cliente = new Cliente();
            cliente.setUsuario(usuario);
            cliente = (Cliente) this.facade.consultar(cliente).getEntidades().get(0);

            Telefone telefone = new Telefone(cliente);

            telefoneDTO.fill(telefone);

            System.out.println("CAraio, e esse tel DTO: " + telefoneDTO.getTelefone());
            System.out.println("CAraio, e esse tel: " + telefone.getTelefone());

            Resultado resultado = this.facade.salvar(telefone);

            Message message = new Message();
            if (resultado.getMensagem() == null) {
                message.setTitle("Sucesso!");
                message.setDescription("Telefone cadastrado com sucesso!");
                return ResponseEntity.ok(message);
            } else {
                message.setTitle("Erro!");
                message.setDescription("Erro ao cadastrar telefone!");
                return ResponseEntity.badRequest().body(message);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }

}
