package com.hotmart.challenge.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotmart.challenge.domain.model.entity.AvaliacaoEntity;
import com.hotmart.challenge.domain.model.entity.ProdutoEntity;
import com.hotmart.challenge.repository.AvaliacaoRepository;

@Service
public class AvaliacaoService {

	@Autowired
	private AvaliacaoRepository avaliacaoRepository;

	/**
	 * Retorna a média de avaliações por produto em um determinado período.
	 * 
	 * @param produto
	 * @param dataInicio
	 * @param dataFim
	 * @return
	 */
	public Double getMediaAvaliacoesPorProduto(ProdutoEntity produto, LocalDateTime dataInicio, LocalDateTime dataFim) {
		List<AvaliacaoEntity> avaliacoes = avaliacaoRepository.findAllNoPeriodoPorProduto(produto, dataInicio, dataFim);
		long quantidade = avaliacoes.size();
		long notaTotal = avaliacoes.stream().mapToInt(AvaliacaoEntity::getNota).sum();
		return (double) notaTotal / quantidade;
	}

}
