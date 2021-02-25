package com.fatec.livrariaecommerce.domain;

public class Documento extends EntidadeDominio {
	private String codigo;
	private String validade;
	private TipoDocumento tipoDocumento;

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
}
