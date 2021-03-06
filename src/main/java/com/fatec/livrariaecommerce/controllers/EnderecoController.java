package com.fatec.livrariaecommerce.controllers;

import com.fatec.livrariaecommerce.models.dto.EnderecoDTO;
import com.fatec.livrariaecommerce.models.utils.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Message> save(
            @RequestBody EnderecoDTO enderecoDto) {
        Message message = new Message();

        message.setTitle("Sucesso");
        message.setDescription("Endereco cadastrado com sucesso!");

        return new ResponseEntity(message, HttpStatus.ACCEPTED);
    }

    /*@CrossOrigin
    @GetMapping(value="/tipoEnderecos")
    public List<TipoEndereco> obterTipoEnderecos() {
        return;
    }*/
}
