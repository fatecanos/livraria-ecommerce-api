package com.fatec.livrariaecommerce.models.dto;

import com.fatec.livrariaecommerce.models.domain.CartaoCredito;
import com.fatec.livrariaecommerce.models.domain.Endereco;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartaoCreditoDTO {

    private int id;
    private String bandeira;
    private Boolean isPreferencial;
    private String codigoSeguranca;
    private String nomeImpressoCartao;
    private String numeroCartao;
    private boolean salvar;

    public void fill(CartaoCredito dominio) {
        dominio.atualizarDados(this.id, this.bandeira, this.numeroCartao, this.codigoSeguranca,
                this.nomeImpressoCartao, this.isPreferencial, this.salvar);
    }

    public static CartaoCreditoDTO from(CartaoCredito cartaoCredito) {
        CartaoCreditoDTO dto = new CartaoCreditoDTO();
        dto.id = cartaoCredito.getId();
        dto.bandeira = cartaoCredito.getBandeira();
        dto.isPreferencial = cartaoCredito.isPreferencial();
        dto.codigoSeguranca = cartaoCredito.getCodigoSeguranca();
        dto.nomeImpressoCartao = cartaoCredito.getNomeImpressoCartao();
        dto.numeroCartao = cartaoCredito.getNumeroCartao();
        dto.salvar = cartaoCredito.isSalvar();
        return dto;
    }

}
