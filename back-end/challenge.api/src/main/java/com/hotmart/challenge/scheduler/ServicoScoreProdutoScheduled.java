package com.hotmart.challenge.scheduler;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hotmart.challenge.service.ProdutoService;

/**
 * Agendador executado de hora em hora responsável por armazenar o score dos
 * produtos. Também fornece a possibilidade de agendar a execuação imediata da
 * tarefa
 * 
 * @author Guilherme Egg
 *
 */
@Component
public class ServicoScoreProdutoScheduled {

	@Autowired
	private TaskScheduler scheduler;

	@Autowired
	private ProdutoService produtoService;

	/**
	 * Agenda a execução imediata da tarefa QuantidadeNoticiasJob
	 * 
	 */
	public void agendarExecucaoImediata() {
		scheduler.schedule(new QuantidadeNoticiasJob(), new Date());
	}

	@EnableScheduling
	private class QuantidadeNoticiasJob implements Runnable {
		private static final String TIME_ZONE = "America/Sao_Paulo";

		@Scheduled(cron = "0 0 */1 * * *", zone = TIME_ZONE)
		public void run() {
			produtoService.armazenarScore();
		}

	}
}
