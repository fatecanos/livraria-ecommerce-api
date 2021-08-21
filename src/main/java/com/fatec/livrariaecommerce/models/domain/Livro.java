package com.fatec.livrariaecommerce.models.domain;

import com.fatec.livrariaecommerce.models.dto.LivroDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table
@Getter @Setter @NoArgsConstructor
public class Livro extends EntidadeDominio {
    @Id
    @SequenceGenerator(
            name="livros_sequence",
            sequenceName="clientes_sequence",
            allocationSize=1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "livros_sequence"
    )
    private int id;
    private LocalDate timeStamp;
    private boolean isAtivo;

    private String autor;
    private String ano;
    private String titulo;
    private String editora;
    private String edicao;
    private String isbn;
    private String numeroPaginas;
    private String sinopse;
    private String codigoBarras;
    private String url;
    private double altura;
    private double largura;
    private double peso;
    private double profundidade;

    @ManyToMany
    @JoinTable
    private List<Categoria> categorias;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="grupo_precificacao_id",
            foreignKey=@ForeignKey(name = "fk_grupo_precificacao_id"))
    private GrupoPrecificacao grupoPrecificacao;

    public Livro(LivroDTO livroDto) {
        this.id = livroDto.getId();
        this.isAtivo = livroDto.isAtivo();
        this.timeStamp = livroDto.getTimestamp();
        this.autor = livroDto.getAutor();
        this.ano = livroDto.getAno();
        this.titulo = livroDto.getTitulo();
        this.editora = livroDto.getEditora();
        this.edicao = livroDto.getEdicao();
        this.isbn = livroDto.getIsbn();
        this.numeroPaginas = livroDto.getQuantidadePaginas();
        this.sinopse = livroDto.getSinopse();
        this.codigoBarras = livroDto.getCodigoBarras();
        this.url = livroDto.getUrl();
    }


}
