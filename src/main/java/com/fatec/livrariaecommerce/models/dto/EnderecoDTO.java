package com.fatec.livrariaecommerce.models.dto;

import com.fatec.livrariaecommerce.models.domain.Cidade;
import com.fatec.livrariaecommerce.models.domain.Endereco;
import com.fatec.livrariaecommerce.models.domain.TipoEndereco;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class EnderecoDTO {

    //TODO: O QUE VAI ACONTECER: SALVAR ENDERECO/ALTERAR E EXCLUIR (PARA AMNHÃ) DEPOIS COMEÇAR CARTÃO

    // ***********************************************************************

    private int id;
    private String nome;
    private String logradouro;
    private String tipoLogradouro;
    private String numero;
    private String bairro;
    private String complemento;
    private String cep;
    private String pais;
    private TipoEndereco tipoEndereco;
    private Cidade cidade;

    // ***********************************************************************

    public void fill(Endereco dominio, Cidade cidade, TipoEndereco tipoEndereco) {
        dominio.atualizarDados(this.id, this.nome, this.logradouro, this.bairro, this.numero, this.cep,
                this.complemento, this.pais, this.tipoLogradouro, cidade, tipoEndereco);
    }

    public static EnderecoDTO from(Endereco endereco) {
        EnderecoDTO dto = new EnderecoDTO();

        dto.id = endereco.getId();
        dto.nome = endereco.getNome();
        dto.logradouro = endereco.getLogradouro();
        dto.tipoLogradouro = endereco.getTipoLogradouro();
        dto.numero = endereco.getNumero();
        dto.bairro = endereco.getBairro();
        dto.complemento = endereco.getComplemento();

        dto.tipoEndereco = endereco.getTipoEndereco();

        dto.cidade = endereco.getCidade();
        dto.pais = endereco.getPais();
        dto.cep = endereco.getCep();

        return dto;
    }

    // ***********************************************************************
}
