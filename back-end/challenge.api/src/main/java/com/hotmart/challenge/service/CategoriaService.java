package com.hotmart.challenge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotmart.challenge.client.NoticiaClient;
import com.hotmart.challenge.domain.model.entity.CategoriaEntity;
import com.hotmart.challenge.domain.model.enuns.ServicoEnum;
import com.hotmart.challenge.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private NoticiaClient noticiaClient;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ExecucaoServicoService execucaoServicoService;

	/**
	 * Armazena a quantidade de notícias por categoria na base de dados. Ao final do
	 * processo grava a data da última execução do serviço
	 */
	@Transactional
	public void armazenarQuantidadeNoticias() {
		List<CategoriaEntity> categorias = categoriaRepository.findAll();
		for (CategoriaEntity categoria : categorias) {
			Long qtdeNoticias = noticiaClient.getTotalNoticiasPorCategoria(categoria);
			categoria.setQuantidadeNoticias(qtdeNoticias);
			categoriaRepository.save(categoria);
		}
		execucaoServicoService.updateDataUltimaExecucao(ServicoEnum.ARMAZENAR_QTDE_NOTICIAS);
	}

}
