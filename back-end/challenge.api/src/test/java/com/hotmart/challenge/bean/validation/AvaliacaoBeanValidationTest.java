package com.hotmart.challenge.bean.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.context.SpringBootTest;

import com.hotmart.challenge.domain.model.entity.AvaliacaoEntity;
import com.hotmart.challenge.domain.model.entity.VendaEntity;

/**
 * Classe de teste Bean Validation Avaliação
 * 
 * @author Guilherme Egg
 *
 */
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class AvaliacaoBeanValidationTest {

	private Validator validator;

	@BeforeAll
	void setUp() {
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	@Test
	@DisplayName("Teste- falha bean validation da avaliação")
	void testarFalhaValidacaoAvaliacao() {
		AvaliacaoEntity avaliacao = new AvaliacaoEntity();
		Set<ConstraintViolation<AvaliacaoEntity>> violations = validator.validate(avaliacao);
		assertFalse(violations.isEmpty());
	}

	@Test
	@DisplayName("Teste- sucesso bean validation da avaliação")
	void testarSucessoValidacaoAvaliacao() {
		AvaliacaoEntity avaliacao = new AvaliacaoEntity(1L, LocalDateTime.now(), (byte) 3, new VendaEntity());
		Set<ConstraintViolation<AvaliacaoEntity>> violations = validator.validate(avaliacao);
		assertTrue(violations.isEmpty());
	}

	@Test
	@DisplayName("Teste- falha bean validation da nota da avaliação limite inferior")
	void testarFalhaValidacaoNotaLimiteInferior() {
		AvaliacaoEntity avaliacao = new AvaliacaoEntity(1L, LocalDateTime.now(), (byte) -1, new VendaEntity());
		Set<ConstraintViolation<AvaliacaoEntity>> violations = validator.validate(avaliacao);
		assertFalse(violations.isEmpty());
	}

	@Test
	@DisplayName("Teste- falha bean validation da nota da avaliação limite superior")
	void testarFalhaValidacaoNotaLimiteSuperior() {
		AvaliacaoEntity avaliacao = new AvaliacaoEntity(1L, LocalDateTime.now(), (byte) 6, new VendaEntity());
		Set<ConstraintViolation<AvaliacaoEntity>> violations = validator.validate(avaliacao);
		assertFalse(violations.isEmpty());
	}

}
