package com.hotmart.challenge.web.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Classe global para o tratamento de exceção
 * 
 * 
 * @author Guilherme Egg
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {

		String mensagem = messageSource.getMessage("data.integrity.violation", null, LocaleContextHolder.getLocale());
		Error error = new Error(ex.getLocalizedMessage(), mensagem);
		return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.PRECONDITION_FAILED);

	}

	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,
			WebRequest request) {
		String mensagem = messageSource.getMessage("empty.result.data.access.exception", null,
				LocaleContextHolder.getLocale());
		Error error = new Error(ex.getLocalizedMessage(), mensagem);
		return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}

	public class Error {

		private String mensagem;
		private String causa;

		public Error(String mensagem, String causa) {
			this.mensagem = mensagem;
			this.causa = causa;
		}

		public String getMensagem() {
			return mensagem;
		}

		public String getCausa() {
			return causa;
		}
	}

}
