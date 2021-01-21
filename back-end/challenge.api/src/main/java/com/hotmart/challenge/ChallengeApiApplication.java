package com.hotmart.challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.hotmart.challenge.scheduler.ServicoScoreProdutoScheduled;

@SpringBootApplication
public class ChallengeApiApplication {

	@Autowired
	private ServicoScoreProdutoScheduled scoreProdutoScheduled;

	public static void main(String[] args) {
		SpringApplication.run(ChallengeApiApplication.class, args);
	}

	/**
	 * Evento após o start da aplicação. Agenda a execução imediata do
	 * ServicoScoreProdutoScheduled
	 * 
	 */
	@EventListener(ApplicationReadyEvent.class)
	public void afterStartup() {
		scoreProdutoScheduled.agendarExecucaoImediata();
	}

}
