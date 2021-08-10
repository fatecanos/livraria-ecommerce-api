package com.fatec.livrariaecommerce.facade;

import com.fatec.livrariaecommerce.dao.CategoriaDao;
import com.fatec.livrariaecommerce.dao.DimensaoDao;
import com.fatec.livrariaecommerce.dao.GrupoPrecificacaoDao;
import com.fatec.livrariaecommerce.dao.LivroDao;
import com.fatec.livrariaecommerce.models.domain.Categoria;
import com.fatec.livrariaecommerce.models.domain.Dimensoes;
import com.fatec.livrariaecommerce.models.domain.GrupoPrecificacao;
import com.fatec.livrariaecommerce.models.domain.Livro;
import com.fatec.livrariaecommerce.models.dto.LivroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LivrosFacade {
    final LivroDao livroDao;
    final GrupoPrecificacaoDao grupoDao;
    final CategoriaDao categoriaDao;
    final DimensaoDao dimensaoDao;

    @Autowired
    public LivrosFacade(
            LivroDao livroDao,
            GrupoPrecificacaoDao grupoDao,
            CategoriaDao categoriaDao,
            DimensaoDao dimensaoDao) {
        this.livroDao = livroDao;
        this.grupoDao = grupoDao;
        this.categoriaDao = categoriaDao;
        this.dimensaoDao = dimensaoDao;
    }

    public void salvar(LivroDTO livroDto) throws Exception {
        Livro originalLivro;
        GrupoPrecificacao grupo;
        List<Categoria> categorias;

        grupo = this.grupoDao.getOne(livroDto.getGrupoPrecificacaoId());
        categorias = livroDto.getCategorias()
                .stream()
                .map(categoria -> this.categoriaDao.getOne(categoria.getId()))
                .collect(Collectors.toList());

        originalLivro = new Livro();
        originalLivro.setAno(livroDto.getAno());
        originalLivro.setAutor(livroDto.getAutor());
        originalLivro.setCategorias(categorias);
        originalLivro.setEdicao(livroDto.getEdicao());
        originalLivro.setEditora(livroDto.getEditora());
        originalLivro.setNumeroPaginas(livroDto.getQuantidadePaginas());
        originalLivro.setIsbn(livroDto.getIsbn());
        originalLivro.setTitulo(livroDto.getTitulo());
        originalLivro.setSinopse(livroDto.getSinopse());
        originalLivro.setCodigoBarras(livroDto.getCodigoBarras());

        //dimensoes
        Dimensoes dimensoes = new Dimensoes();
        dimensoes.setAltura(livroDto.getDimensoes().getAltura());
        dimensoes.setLargura(livroDto.getDimensoes().getLargura());
        dimensoes.setPeso(livroDto.getDimensoes().getPeso());
        dimensoes.setProfundidade(livroDto.getDimensoes().getProfundidade());

        originalLivro.setDimensoes(dimensoes);
        originalLivro.setGrupoPrecificacao(grupo);

        originalLivro.setUrl(livroDto.getUrl());

        originalLivro.setTimeStamp(LocalDate.now());
        originalLivro.setAtivo(true);

        this.livroDao.save(originalLivro);
    }

    public List<Livro> obterLivros() {
        return this.livroDao.findAll();
    }

    public LivroDTO obterLivroPorId(Integer id) throws Exception {
        Optional<Livro> livro = this.livroDao.findById(id);
        if(livro.isEmpty()) throw new Exception("erro ao obter livro");
        return new LivroDTO(livro.get());
    }

    public void inativarLivro(int livroId) throws Exception {
        Livro livro = this.livroDao.getOne(livroId);
        if(livro.equals(null)) {
            throw new Exception("erro: livro não encontrado");
        }
        livro.setAtivo(false);
        this.livroDao.save(livro);
    }

    public void atualizar(LivroDTO livroDto) throws Exception {
        Livro livro = this.livroDao.getOne(livroDto.getId());

        if(!livro.isAtivo()) {
            throw new Exception("Erro: livro não encontrado");
        }

        livro.setTitulo(livroDto.getTitulo());
        livro.setUrl(livroDto.getUrl());
        livro.setSinopse(livroDto.getSinopse());
        livro.setIsbn(livroDto.getIsbn());
        livro.setAno(livroDto.getAno());
        livro.setNumeroPaginas(livroDto.getQuantidadePaginas());
        livro.setEditora(livroDto.getEditora());
        livro.setEdicao(livroDto.getEdicao());
        livro.setAutor(livroDto.getAutor());

        livro.setCategorias(livroDto.getCategorias());
        livro.setGrupoPrecificacao(
                this.grupoDao.getOne(livroDto.getGrupoPrecificacaoId())
        );

        Dimensoes dimensoes = this.dimensaoDao.getOne(livroDto.getDimensoes().getId());
        dimensoes.setProfundidade(livroDto.getDimensoes().getProfundidade());
        dimensoes.setPeso(livroDto.getDimensoes().getPeso());
        dimensoes.setLargura(livroDto.getDimensoes().getLargura());

        livro.setDimensoes(dimensoes);

        this.livroDao.save(livro);
    }
}
