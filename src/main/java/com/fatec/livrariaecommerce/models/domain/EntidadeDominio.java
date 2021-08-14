package com.fatec.livrariaecommerce.models.domain;

import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor

public abstract class EntidadeDominio {

	private LocalDate timeStamp;
	private boolean isAtivo;

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
