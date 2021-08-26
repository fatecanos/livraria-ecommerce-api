package com.fatec.livrariaecommerce.controllers;

import com.fatec.livrariaecommerce.models.utils.Message;
import com.fatec.livrariaecommerce.models.domain.GrupoPrecificacao;
import com.fatec.livrariaecommerce.models.domain.Livro;
import com.fatec.livrariaecommerce.models.dto.CategoriaDTO;
import com.fatec.livrariaecommerce.models.dto.LivroDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivrosController {

//    private final CategoriasFacade categoriasFacade;
//    private final GrupoPrecificacaoFacade gruposPrecoFacade;
//    private final LivrosFacade livrosFacade;

//    @Autowired
//    public LivrosController(
//            LivrosFacade livrosFacade,
//            CategoriasFacade categoriasFacade,
//            GrupoPrecificacaoFacade gruposPrecoFacade) {
//        this.livrosFacade = livrosFacade;
//        this.categoriasFacade = categoriasFacade;
//        this.gruposPrecoFacade = gruposPrecoFacade;
//    }

    @CrossOrigin
    @GetMapping("/categorias")
    public ResponseEntity<List<CategoriaDTO>> obterCategorias() {
//        List<CategoriaDTO> categorias;
//        try {
//            categorias = this.categoriasFacade.listar();
//
//            return ResponseEntity.ok(categorias);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.notFound().build();
//        }

        return null;
    }

    @CrossOrigin
    @GetMapping("/grupo-precificacao")
    public ResponseEntity<List<GrupoPrecificacao>> obterGruposPreficicacao() {
//        List<GrupoPrecificacao> grupos;
//        grupos = this.gruposPrecoFacade.obterGrupos();
//        if(grupos.isEmpty())
//            return ResponseEntity.notFound().build();
//        return ResponseEntity.ok(grupos);
        return null;
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Message>
    salvarLivro(@RequestBody LivroDTO livroDto) throws Exception {
//        Message mensagem = new Message();
//        mensagem.setTitle("sucesso");
//        mensagem.setDescription("cadastro do livro efetuado");
//
//        this.livrosFacade.salvar(livroDto);
//
//        return ResponseEntity.ok(mensagem);
        return null;

    }

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<Livro>> obterLivros() {
//        List<Livro> livros = this.livrosFacade.obterLivros();
//        if(livros.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(livros);
        return null;
    }

    @CrossOrigin
    @DeleteMapping
    public void inativar(@RequestParam int id) throws Exception {
//        this.livrosFacade.inativarLivro(id);

    }

    @CrossOrigin
    @PutMapping
    public ResponseEntity<Message>
    atualizar(@RequestBody LivroDTO livroDto) throws Exception {
//        System.out.println("Chegou o DTO"+ livroDto.toString());
//        this.livrosFacade.atualizar(livroDto);
//        return ResponseEntity.ok().build();
        return null;
    }

}
