package com.hotmart.challenge.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hotmart.challenge.service.CategoriaService;

/**
 * Agendador executado de 6 em 6 horas responsável por armazenar a quantidade de
 * notícias
 * 
 * @author Guilherme Egg
 *
 */
@Component
@EnableScheduling
public class ServicoQuantidadeNoticiasScheduled {

	@Autowired
	CategoriaService categoriaService;

	private static final String TIME_ZONE = "America/Sao_Paulo";

	@Scheduled(cron = "0 0 */6 * * *", zone = TIME_ZONE)
	public void executar() {
		categoriaService.armazenarQuantidadeNoticias();
	}

}
