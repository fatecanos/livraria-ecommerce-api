package com.fatec.livrariaecommerce.models.domain;

import com.fatec.livrariaecommerce.models.domain.Cidade;
import com.fatec.livrariaecommerce.models.domain.Cliente;
import com.fatec.livrariaecommerce.models.domain.EntidadeDominio;
import com.fatec.livrariaecommerce.models.domain.TipoEndereco;
import com.fatec.livrariaecommerce.models.dto.EnderecoDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor

@Getter
@Setter

@Entity
public class Endereco extends EntidadeDominio {

    // ***********************************************************************

    @Id
    @SequenceGenerator(name = "enderecos_sequences", sequenceName = "encerecos_sequences", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "enderecos_sequences")
    private int id;
    private String nome;
    private String logradouro;
    private String bairro;
    private String numero;
    private String cep;
    private String complemento;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tipo_endereco_id")
    private TipoEndereco tipoEndereco;

    @JoinColumn(name = "cliente", foreignKey = @ForeignKey(name = "fk_endereco_cliente"))
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    private Cliente cliente;

    // ***********************************************************************

    public Endereco(Cliente cliente) {
        this.cliente = cliente;
    }

    // ***********************************************************************

    public void atualizarDados(int id, String nome, String logradouro, String bairro, String numero, String cep, String complemento,
                    Cidade cidade, TipoEndereco tipoEndereco) {
        this.id = id;
        this.nome = nome;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.numero = numero;
        this.cep = cep;
        this.complemento = complemento;
        this.cidade = cidade;
        this.tipoEndereco = tipoEndereco;
    }

    // ***********************************************************************
}
