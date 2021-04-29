package com.fatec.livrariaecommerce.models.domain;

import javax.persistence.*;

@Entity
public class Documento extends EntidadeDominio {

	@Id
	@SequenceGenerator(
			name="documentos_sequence",
			sequenceName="documentos_sequence",
			allocationSize=1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "documentos_sequence"
	)
	private int id;
	private String codigo;
	private String validade;

	@OneToOne(cascade = CascadeType.ALL)
	private TipoDocumento tipoDocumento;

	public Documento() {
	}

	public Documento(String codigo, TipoDocumento tipoDocumento) {
		this.codigo = codigo;
		this.tipoDocumento = tipoDocumento;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getValidade() {
		return validade;
	}

	public void setValidade(String validade) {
		this.validade = validade;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
