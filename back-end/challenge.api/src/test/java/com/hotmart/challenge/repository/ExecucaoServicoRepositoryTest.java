package com.hotmart.challenge.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hotmart.challenge.domain.model.entity.ExecucaoServicoEntity;
import com.hotmart.challenge.domain.model.enuns.ServicoEnum;

/**
 * Classe de teste Execucao Servico Repository
 * 
 * @author Guilherme Egg
 *
 */
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class ExecucaoServicoRepositoryTest {

	@Autowired
	private ExecucaoServicoRepository execucaoServicoRepository;

	@Test
	@DisplayName("Teste- buscar serviço ARMAZENAR_QTDE_NOTICIAS")
	void testarSucessoBuscarServicoArmazenarQtdeNoticias() {
		Optional<ExecucaoServicoEntity> servico = execucaoServicoRepository
				.findById((long) ServicoEnum.ARMAZENAR_QTDE_NOTICIAS.getIdentificador());
		assertTrue(servico.isPresent());
	}

	@Test
	@DisplayName("Teste- buscar serviço ARMAZENAR_SCORE_PRODUTO")
	void testarSucessoBuscarServicoArmazenarScoreProduto() {
		Optional<ExecucaoServicoEntity> servico = execucaoServicoRepository
				.findById((long) ServicoEnum.ARMAZENAR_SCORE_PRODUTO.getIdentificador());
		assertTrue(servico.isPresent());
	}

}
