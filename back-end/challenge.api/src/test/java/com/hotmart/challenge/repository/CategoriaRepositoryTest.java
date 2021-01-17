package com.hotmart.challenge.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hotmart.challenge.domain.model.entity.CategoriaEntity;

/**
 * Classe de teste Categoria Repository
 * 
 * @author Guilherme Egg
 *
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
class CategoriaRepositoryTest {

	private CategoriaEntity categoria;

	private static final String NOME_TESTE = "Categoria Teste";

	private static final String NOME_TESTE_UPDATE = "Categoria Teste 2";

	@Autowired
	private CategoriaRepository categoriaRepository;

	@BeforeAll
	void setUp() {
		categoria = new CategoriaEntity();
		categoria.setNome(NOME_TESTE);
	}

	@AfterAll
	void afterAll() {
		categoriaRepository.delete(categoria);
	}

	@Test
	@DisplayName("Teste- incluir categoria")
	@Order(1)
	void incluir() {
		categoria = categoriaRepository.save(categoria);
		assertNotNull(categoria.getId());
	}

	@Test
	@DisplayName("Teste- buscar categoria por Id")
	@Order(2)
	void buscarPorId() {
		assertTrue(categoriaRepository.findById(categoria.getId()).isPresent());
	}

	@Test
	@DisplayName("Teste- alterar nome da categoria")
	@Order(2)
	void alterar() {
		CategoriaEntity lCategoria = categoriaRepository.findById(this.categoria.getId()).get();
		lCategoria.setNome(NOME_TESTE_UPDATE);
		categoriaRepository.save(lCategoria);

		lCategoria = categoriaRepository.findById(this.categoria.getId()).get();
		assertEquals(NOME_TESTE_UPDATE, lCategoria.getNome());
	}

}
