package com.fatec.livrariaecommerce.models.dto;


import com.fatec.livrariaecommerce.models.domain.ConsultarGeneroCliente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ConsultarGeneroClienteDTO {

    private double masculino;
    private double feminino;

    public static ConsultarGeneroClienteDTO from(ConsultarGeneroCliente consultarGeneroCliente){
        ConsultarGeneroClienteDTO dto = new ConsultarGeneroClienteDTO();
        dto.feminino = consultarGeneroCliente.getFeminino();
        dto.masculino = consultarGeneroCliente.getMasculino();
        return dto;
    }

}
