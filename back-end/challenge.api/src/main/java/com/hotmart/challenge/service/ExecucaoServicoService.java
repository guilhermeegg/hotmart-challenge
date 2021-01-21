package com.hotmart.challenge.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotmart.challenge.domain.model.entity.ExecucaoServicoEntity;
import com.hotmart.challenge.domain.model.enuns.ServicoEnum;
import com.hotmart.challenge.repository.ExecucaoServicoRepository;
import com.hotmart.challenge.util.MessageUtil;

@Service
public class ExecucaoServicoService {

	@Autowired
	private ExecucaoServicoRepository servicoRepository;

	@Autowired
	private MessageUtil messageUtil;

	public ExecucaoServicoEntity findById(Long id) {
		Optional<ExecucaoServicoEntity> optionalServicoEntity = servicoRepository.findById(id);
		if (optionalServicoEntity.isPresent()) {
			return optionalServicoEntity.get();
		} else {
			throw new EntityNotFoundException(messageUtil.getMessage(MessageUtil.EXECUCAO_SERVICO_NAO_ENCONTRADO));
		}
	}

	/**
	 * Atualiza a data da última execução do serviço com a data atual
	 * 
	 * @param servicoEnum
	 */
	public void updateDataUltimaExecucao(ServicoEnum servicoEnum) {
		ExecucaoServicoEntity servicoEntity = findById((long) servicoEnum.getIdentificador());
		servicoEntity.setDataUltimaExecucao(LocalDateTime.now());
		servicoRepository.save(servicoEntity);
	}

	/**
	 * Retorna verdadeiro se a data da última execução do serviço for nula ou
	 * anterior a data atual com horário minimo (00:00)
	 * 
	 * @param armazenarQtdeNoticias
	 * @return
	 */
	public boolean isPrecisaExecuar(ServicoEnum servicoEnum) {
		ExecucaoServicoEntity servicoEntity = findById((long) servicoEnum.getIdentificador());
		LocalDateTime dataAtual = LocalDateTime.now().with(LocalTime.MIN);
		LocalDateTime dataUltimaExecucao = servicoEntity.getDataUltimaExecucao();

		return dataUltimaExecucao == null || dataUltimaExecucao.isBefore(dataAtual);

	}

}
