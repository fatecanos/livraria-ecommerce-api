package com.fatec.livrariaecommerce.controllers;

import com.fatec.livrariaecommerce.domain.TipoEndereco;
import com.fatec.livrariaecommerce.dto.EnderecoDTO;
import com.fatec.livrariaecommerce.models.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    public EnderecoController() {

    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Message> save(
            @RequestBody EnderecoDTO enderecoDto) {
        Message message = new Message();

        System.out.println(enderecoDto);

        message.setTitle("Sucesso");
        message.setDescription("Endereco cadastrado com sucesso!");

        return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
    }

    @CrossOrigin
    @GetMapping(value="/tipoEnderecos")
    public List<TipoEndereco> obterTipoEnderecos() {
        return this.obterTipoEnderecos();
    }
}
