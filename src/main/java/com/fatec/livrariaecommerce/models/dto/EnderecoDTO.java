package com.fatec.livrariaecommerce.models.dto;

import com.fatec.livrariaecommerce.models.domain.Cidade;
import com.fatec.livrariaecommerce.models.domain.Endereco;
import com.fatec.livrariaecommerce.models.domain.TipoEndereco;
import lombok.Builder;
import lombok.Data;

@Data
public class EnderecoDTO {
    private int id;
    private String logradouro;
    private String numero;
    private String bairro;
    private String complemento;
    private String cep;
    private TipoEndereco tipoEndereco;
    private Cidade cidade;

    public EnderecoDTO(Endereco endereco) {
        this.id = endereco.getId();
        this.logradouro = endereco.getLogradouro();
        this.numero = endereco.getNumero();
        this.bairro = endereco.getBairro();
        this.complemento = endereco.getComplemento();
        this.tipoEndereco = endereco.getTipoEndereco();
        this.cidade = endereco.getCidade();
        this.cep = endereco.getCep();
    }
}
