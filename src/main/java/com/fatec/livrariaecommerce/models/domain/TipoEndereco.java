package com.fatec.livrariaecommerce.models.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "tipo_endereco")
public class TipoEndereco extends EntidadeDominio {
	private String descricao;
}
