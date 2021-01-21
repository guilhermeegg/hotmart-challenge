package com.hotmart.challenge.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hotmart.challenge.service.ProdutoService;

/**
 * Agendador executado de hora em hora responsável por armazenar o score dos produtos
 * 
 * @author Guilherme Egg
 *
 */
@Component
@EnableScheduling
public class ServicoScoreProdutoScheduled {

	@Autowired
	ProdutoService produtoService;

	private static final String TIME_ZONE = "America/Sao_Paulo";

	@Scheduled(cron = "0 0 */1 * * *", zone = TIME_ZONE)
	public void executar() {
		produtoService.armazenarScore();
	}

}
