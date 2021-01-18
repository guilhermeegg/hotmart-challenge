package com.hotmart.challenge.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

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
import com.hotmart.challenge.domain.model.entity.ProdutoEntity;

/**
 * Classe de teste Produto Repository
 * 
 * @author Guilherme Egg
 *
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
class ProdutoRepositoryTest {

	private ProdutoEntity produto;

	private CategoriaEntity categoria;

	private static final String NOME_TESTE = "Teste";

	private static final String NOME_TESTE_UPDATE = "Teste 2";

	private static final String DESCRICAO_TESTE = "Teste descrição do produto";

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@BeforeAll
	void setUp() {
		produto = new ProdutoEntity();
		produto.setNome(NOME_TESTE);
		produto.setDescricao(DESCRICAO_TESTE);
		produto.setDataCriacao(LocalDateTime.now());

		categoria = new CategoriaEntity();
		categoria.setNome(NOME_TESTE);
	}

	@AfterAll
	void afterAll() {
		produtoRepository.delete(produto);
		categoriaRepository.delete(categoria);
	}

	@Test
	@DisplayName("Teste- incluir produto")
	@Order(1)
	void incluir() {
		categoria = categoriaRepository.save(categoria);
		produto.setCategoria(categoria);
		produto = produtoRepository.save(produto);
		assertNotNull(produto.getId());
	}

	@Test
	@DisplayName("Teste- buscar produto por Id")
	@Order(2)
	void buscarPorId() {
		assertTrue(produtoRepository.findById(produto.getId()).isPresent());
	}

	@Test
	@DisplayName("Teste- alterar nome do produto")
	@Order(2)
	void alterar() {
		ProdutoEntity lProduto = produtoRepository.findById(this.produto.getId()).get();
		lProduto.setNome(NOME_TESTE_UPDATE);
		produtoRepository.save(lProduto);

		lProduto = produtoRepository.findById(this.produto.getId()).get();
		assertEquals(NOME_TESTE_UPDATE, lProduto.getNome());
	}

}
