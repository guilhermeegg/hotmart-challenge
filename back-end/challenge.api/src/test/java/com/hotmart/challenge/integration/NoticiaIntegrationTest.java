package com.hotmart.challenge.integration;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hotmart.challenge.client.NoticiaClient;
import com.hotmart.challenge.domain.model.entity.CategoriaEntity;
import com.hotmart.challenge.repository.CategoriaRepository;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class NoticiaIntegrationTest {

	@Autowired
	NoticiaClient newsClient;

	@Autowired
	CategoriaRepository categoriaRepository;

	@Test
	@Disabled("Por padrão o teste está desabilitado afim de evitar consultas desnecessárias a NewsAPI")
	@DisplayName("Teste de Integração obter quantidade de notícias por categoria - NewsAPI")
	void testarSucessoQuantidadeNoticiasPorCategoria() {
		Optional<CategoriaEntity> categoria = categoriaRepository.findById(1L);
		if (!categoria.isPresent()) {
			assertTrue(newsClient.getTotalNoticiasPorCategoria(categoria.get()) >= 0);
		} else {
			fail("Não encontrou a categoria na base de dados");
		}

	}
}
