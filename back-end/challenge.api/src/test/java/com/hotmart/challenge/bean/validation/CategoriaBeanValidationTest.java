package com.hotmart.challenge.bean.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

import com.hotmart.challenge.domain.model.entity.CategoriaEntity;

/**
 * Classe de teste Bean Validation Categoria
 * 
 * @author Guilherme Egg
 *
 */
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class CategoriaBeanValidationTest {

	private Validator validator;

	@BeforeAll
	private void setUp() {
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	@Test
	@DisplayName("Teste- falha bean validation da categoria")
	public void testarFalhaValidacaoCategoria() {
		CategoriaEntity categoria = new CategoriaEntity();
		Set<ConstraintViolation<CategoriaEntity>> violations = validator.validate(categoria);
		assertFalse(violations.isEmpty());
	}

	@Test
	@DisplayName("Teste- sucesso bean validation da categoria")
	public void testarSucessoValidacaoCategoria() {
		CategoriaEntity categoria = new CategoriaEntity(0L, "Categoria Teste");
		Set<ConstraintViolation<CategoriaEntity>> violations = validator.validate(categoria);
		assertTrue(violations.isEmpty());
	}

}
