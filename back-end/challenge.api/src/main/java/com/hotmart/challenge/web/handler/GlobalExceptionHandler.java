package com.hotmart.challenge.web.handler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

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
		return getStandardResponseEntity("data.integrity.violation", HttpStatus.PRECONDITION_FAILED, ex);
	}

	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex) {
		return getStandardResponseEntity("result.not.found", HttpStatus.NOT_FOUND, ex);
	}
	
	@ExceptionHandler({EntityNotFoundException.class })
	public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
		return getStandardResponseEntity("result.not.found", HttpStatus.NOT_FOUND, ex);
	}
	

	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
		return getStandardResponseEntity("constraint.violation.exception", HttpStatus.BAD_REQUEST, ex);
	}

	private ResponseEntity<Object> getStandardResponseEntity(String textoMensagem, HttpStatus httpStatus,
			Exception ex) {
		String mensagem = messageSource.getMessage(textoMensagem, null, LocaleContextHolder.getLocale());
		Error error = new Error(ex.getLocalizedMessage(), mensagem);
		return new ResponseEntity<>(error, new HttpHeaders(), httpStatus);
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
