package com.fatec.livrariaecommerce.controllers;

import com.fatec.livrariaecommerce.facade.IFacade;
import com.fatec.livrariaecommerce.models.domain.Resultado;
import com.fatec.livrariaecommerce.models.utils.Message;
import com.fatec.livrariaecommerce.models.domain.Livro;
import com.fatec.livrariaecommerce.models.dto.LivroDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor

@CrossOrigin
@RestController
@RequestMapping("/livros")
public class LivrosController {

    private final IFacade facade;

    // ***********************************************************************

    @PostMapping
    public ResponseEntity<Message> salvarLivro(@RequestBody LivroDTO livroDTO) {
        try {
            Message message = new Message();
            Livro livro = new Livro();
            livroDTO.fill(livro);
            Resultado resultado = this.facade.salvar(livro);
            if (resultado.getMensagem() == null) {
                message.setTitle("Sucesso");
                message.setDescription("Livro cadastrado com sucesso!");
                return ResponseEntity.ok(message);
            } else {
                message.setTitle("Erro");
                message.setDescription(resultado.getMensagem());
                return ResponseEntity.badRequest().body(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping
    public ResponseEntity<List<LivroDTO>> consultarLivros(){
        try{
            Livro livro = new Livro();
            livro.setAtivo(true);
            List<LivroDTO> livroDTOList = this.facade.consultar(livro).getEntidades().stream().map(lvr -> {
                return LivroDTO.from((Livro) lvr);
            }).collect(Collectors.toList());
            return ResponseEntity.ok(livroDTOList);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("detalhes/{idLivro}")
    public ResponseEntity<LivroDTO> consultarLivroPorID(@PathVariable int idLivro){
        try{
            Livro livro = new Livro();
            livro.setId(idLivro);
            Resultado resultado = this.facade.consultar(livro);
            livro = (Livro) resultado.getEntidades().get(0);
            return ResponseEntity.ok(LivroDTO.from(livro));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

}
