package com.hotmart.challenge.domain.model.enuns;

import lombok.Getter;

@Getter
public enum ServicoEnum {

	ARMAZENAR_QTDE_NOTICIAS(1), ARMAZENAR_SCORE_PRODUTO(2);

	private int identificador;

	private ServicoEnum(int identificador) {
		this.identificador = identificador;
	}
}
