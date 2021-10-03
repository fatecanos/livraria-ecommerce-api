package com.fatec.livrariaecommerce.models.dto;

import com.fatec.livrariaecommerce.models.domain.Cliente;
import com.fatec.livrariaecommerce.models.domain.Cupom;
import com.fatec.livrariaecommerce.models.domain.TipoCupom;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CupomDTO {

    private int id;
    private String nome;
    private String codigo;
    private double valor;
    private TipoCupom tipoCupom;

    public void fill(Cupom dominio, Cliente cliente) {

        if (this.tipoCupom == TipoCupom.PROMOCIONAL) {
            cliente = null;
        }

        if(this.tipoCupom == TipoCupom.TROCA){
            this.codigo = "";
        }
        dominio.atualizarDados(this.id, this.nome, this.valor, this.codigo, cliente, this.tipoCupom);
    }

    public static CupomDTO from(Cupom dominio) {
        CupomDTO dto = new CupomDTO();

        dto.id = dominio.getId();
        dto.nome = dominio.getNome();
        dto.valor = dominio.getValor();
        dto.codigo = dominio.getCodigo();
        dto.tipoCupom = dominio.getTipoCupom();
        return dto;
    }

}
