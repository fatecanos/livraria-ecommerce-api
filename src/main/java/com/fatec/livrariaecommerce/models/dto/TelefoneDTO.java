package com.fatec.livrariaecommerce.models.dto;

import com.fatec.livrariaecommerce.models.domain.Telefone;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TelefoneDTO {

    private int id;
    private String telefone;

    public void fill(Telefone dominio) {
        dominio.atualizarDados(this.id, this.telefone);
    }

    public static TelefoneDTO from(Telefone telefone){
        TelefoneDTO dto = new TelefoneDTO();
        dto.id = telefone.getId();
        dto.telefone = telefone.getTelefone();
        return dto;
    }

}
