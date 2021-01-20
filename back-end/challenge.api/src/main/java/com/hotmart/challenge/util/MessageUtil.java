package com.hotmart.challenge.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class MessageUtil {

	@Autowired
	private MessageSource messageSource;

	public static final String PRODUTO_NAO_ENCONTRADO = "produto.nao.encontrado";

	public static final String DATA_INTEGRITY_VIOLATION = "data.integrity.violation";

	public static final String RESULT_NOT_FOUND = "result.not.found";

	public static final String CONSTRAINT_VIOLATION_EXCEPTION = "constraint.violation.exception";

	public static final String TAMANHO_LIMITE_PAGINACAO = "tamanho.limite.paginacao";

	public String getMessage(String textoMensagem) {
		return messageSource.getMessage(textoMensagem, null, LocaleContextHolder.getLocale());
	}

}
