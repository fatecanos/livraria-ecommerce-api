package com.fatec.livrariaecommerce.models.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class Resultado {
    private String mensagem;
    private List<EntidadeDominio> entidades = new ArrayList<>();
}
