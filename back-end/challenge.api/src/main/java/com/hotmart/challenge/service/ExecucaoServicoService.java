package com.hotmart.challenge.service;

import java.time.LocalDateTime;
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

	public void updateDataUltimaExecucao(ServicoEnum servicoEnum) {
		Optional<ExecucaoServicoEntity> optionalServicoEntity = servicoRepository
				.findById((long) servicoEnum.getIdentificador());
		if (optionalServicoEntity.isPresent()) {
			ExecucaoServicoEntity servicoEntity = optionalServicoEntity.get();
			servicoEntity.setDataUltimaExecucao(LocalDateTime.now());
			servicoRepository.save(servicoEntity);
		} else {
			throw new EntityNotFoundException(messageUtil.getMessage(MessageUtil.EXECUCAO_SERVICO_NAO_ENCONTRADO));
		}
	}

}
