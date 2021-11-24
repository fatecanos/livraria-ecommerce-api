package com.fatec.livrariaecommerce.controllers;

import com.fatec.livrariaecommerce.facade.IFacade;
import com.fatec.livrariaecommerce.models.domain.*;
import com.fatec.livrariaecommerce.models.dto.CategoriaDTO;
import com.fatec.livrariaecommerce.models.dto.GrupoPrecificacaoDTO;
import com.fatec.livrariaecommerce.models.dto.TelefoneDTO;
import com.fatec.livrariaecommerce.models.utils.Message;
import com.fatec.livrariaecommerce.models.dto.LivroDTO;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Logger;
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
    private final Logger logger;


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
                logger.info("Livro cadastrado com sucesso:" +
                        "\nTítulo: " + ((Livro) resultado.getEntidades().get(0)).getTitulo() +
                        "\nAutor: " + ((Livro) resultado.getEntidades().get(0)).getAutor() +
                        "\nEditora: " + ((Livro) resultado.getEntidades().get(0)).getEditora() +
                        "\nQuantidade em estoque: " + ((Livro) resultado.getEntidades().get(0)).getEstoque());
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

    @PutMapping(path = "/reporestoque")
    public ResponseEntity<Message> reporEstoqueLivro(@RequestBody LivroDTO livroDTO) {
        try {
            Livro livro = new Livro();
            livroDTO.fill(livro);
            Resultado resultado = this.facade.alterar(livro);
            Message message = new Message();
            if (resultado.getMensagem() == null) {
                message.setTitle("Sucesso!");
                message.setDescription("Estoque reposto com sucesso!");
                logger.info("Estoque reposto com sucesso:" +
                        "\nNova quantidade em estoque: " + ((Livro) resultado.getEntidades().get(0)).getEstoque());
                return ResponseEntity.ok(message);
            } else {
                message.setTitle("Erro!");
                message.setDescription("Erro ao repor estoque!");
                return ResponseEntity.badRequest().body(message);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping
    public ResponseEntity<List<LivroDTO>> consultarLivros() {
        try {
            Livro livro = new Livro();
            livro.setAtivo(true);
            List<LivroDTO> livroDTOList = this.facade.consultar(livro).getEntidades().stream().map(lvr -> {
                return LivroDTO.from((Livro) lvr);
            }).collect(Collectors.toList());
            return ResponseEntity.ok(livroDTOList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("detalhes/{idLivro}")
    public ResponseEntity<LivroDTO> consultarLivroPorID(@PathVariable int idLivro) {
        try {
            Livro livro = new Livro();
            livro.setId(idLivro);
            Resultado resultado = this.facade.consultar(livro);
            livro = (Livro) resultado.getEntidades().get(0);
            return ResponseEntity.ok(LivroDTO.from(livro));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/grupoprecificacao")
    public ResponseEntity<List<GrupoPrecificacaoDTO>> consultarGrupoPrecificacao() {
        try {
            GrupoPrecificacao grupoPrecificacao = new GrupoPrecificacao();
            grupoPrecificacao.setAtivo(true);
            List<GrupoPrecificacaoDTO> grupoPrecificacaoDTOList = this.facade.consultar(grupoPrecificacao)
                    .getEntidades()
                    .stream()
                    .map(gp -> {
                        return GrupoPrecificacaoDTO.from((GrupoPrecificacao) gp);
                    }).collect(Collectors.toList());
            return ResponseEntity.ok(grupoPrecificacaoDTOList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/categorias")
    public ResponseEntity<List<CategoriaDTO>> consultarCategorias() {
        try {
            Categoria categoria = new Categoria();
            categoria.setAtivo(true);
            List<CategoriaDTO> categoriaDTOList = this.facade.consultar(categoria)
                    .getEntidades()
                    .stream()
                    .map(cat -> {
                        return CategoriaDTO.from((Categoria) cat);
                    }).collect(Collectors.toList());
            return ResponseEntity.ok(categoriaDTOList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }


}
