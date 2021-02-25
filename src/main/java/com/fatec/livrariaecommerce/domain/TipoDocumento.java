package com.fatec.livrariaecommerce.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data @AllArgsConstructor
public class TipoDocumento extends EntidadeDominio {
	private String descricao;
	private String nome;


}
