package com.fatec.livrariaecommerce.models.domain;

import java.time.LocalDate;

public abstract class EntidadeDominio {
	private LocalDate timeStamp;
	private boolean isAtivo;

	public EntidadeDominio() {
	}

	public EntidadeDominio(LocalDate timeStamp, boolean isAtivo) {
		this.timeStamp = timeStamp;
		this.isAtivo = isAtivo;
	}

	public LocalDate getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDate timeStamp) {
		this.timeStamp = timeStamp;
	}

	public boolean isAtivo() {
		return isAtivo;
	}

	public void setAtivo(boolean ativo) {
		isAtivo = ativo;
	}
}
