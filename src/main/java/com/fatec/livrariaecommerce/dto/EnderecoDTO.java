package com.fatec.livrariaecommerce.dto;

import com.fatec.livrariaecommerce.domain.Cidade;
import com.fatec.livrariaecommerce.domain.TipoEndereco;
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

    @Builder(builderMethodName = "montar")
    public EnderecoDTO(int id, String logradouro, String numero,
                       String bairro, String complemento,
                       TipoEndereco tipoEndereco, Cidade cidade, String cep) {
        this.id = id;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.complemento = complemento;
        this.tipoEndereco = tipoEndereco;
        this.cidade = cidade;
        this.cep = cep;
    }
}
