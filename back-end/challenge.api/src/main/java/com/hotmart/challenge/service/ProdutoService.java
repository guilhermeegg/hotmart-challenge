package com.hotmart.challenge.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotmart.challenge.domain.model.entity.CategoriaEntity;
import com.hotmart.challenge.domain.model.entity.ProdutoEntity;
import com.hotmart.challenge.domain.model.enuns.ServicoEnum;
import com.hotmart.challenge.repository.ProdutoRepository;
import com.hotmart.challenge.repository.VendaRepository;
import com.hotmart.challenge.util.MessageUtil;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	@Lazy
	private ExecucaoServicoService execucaoServico;

	@Autowired
	@Lazy
	private CategoriaService categoriaService;

	@Autowired
	@Lazy
	private AvaliacaoService avaliacaoService;

	@Autowired
	@Lazy
	private VendaRepository vendaRepository;

	@Autowired
	private MessageUtil messageUtil;

	public ProdutoEntity findById(Long id) {
		Optional<ProdutoEntity> produto = produtoRepository.findById(id);
		if (!produto.isPresent()) {
			throw new EntityNotFoundException(messageUtil.getMessage(MessageUtil.PRODUTO_NAO_ENCONTRADO));
		}
		return produto.get();
	}

	public void deleteById(Long id) {
		produtoRepository.deleteById(id);
	}

	public ProdutoEntity save(ProdutoEntity produto) {
		return produtoRepository.save(produto);
	}

	public ProdutoEntity update(Long id, ProdutoEntity produtoEdit) {
		ProdutoEntity produto = findById(id);
		BeanUtils.copyProperties(produtoEdit, produto, "id", "score");
		return produtoRepository.save(produto);
	}

	public Page<ProdutoEntity> findAll(Pageable page) {
		return produtoRepository.findAll(page);
	}

	/**
	 * Armazena o score dos produtos na base de dados. Ao final do processo grava a
	 * data da última execução do serviço. OBS: se necessário força a execução do
	 * serviço para armazenar a quantidade de notícias.
	 */
	@Transactional
	public void armazenarScore() {
		if (execucaoServico.isPrecisaExecuar(ServicoEnum.ARMAZENAR_QTDE_NOTICIAS)) {
			categoriaService.armazenarQuantidadeNoticias();
		}

		Map<Long, CategoriaEntity> mapCategorias = categoriaService.findAll().stream()
				.collect(Collectors.toMap(CategoriaEntity::getId, categoria -> categoria));

		List<ProdutoEntity> produtos = produtoRepository.findAll();

		LocalDateTime dataUltimo12Meses = LocalDateTime.now().plusMonths(-12);
		LocalDateTime dataAtual = LocalDateTime.now();

		for (ProdutoEntity produto : produtos) {
			produto.setScore(getScoreProduto(mapCategorias, dataUltimo12Meses, dataAtual, produto));
			produtoRepository.save(produto);
		}

		execucaoServico.updateDataUltimaExecucao(ServicoEnum.ARMAZENAR_SCORE_PRODUTO);

	}

	/**
	 * Obtém o score do produto de acordo com a fórmula:
	 * 
	 * X = Média de avaliação do produto nos últimos 12 meses Y = Quantidade de
	 * vendas/dias que o produto existe Z = Quantidade de notícias da categoria do
	 * produto no dia corrente Score = X + Y + Z
	 * 
	 * 
	 * @param mapCategorias
	 * @param dataUltimo12Meses
	 * @param dataAtual
	 * @param produto
	 */
	private float getScoreProduto(Map<Long, CategoriaEntity> mapCategorias, LocalDateTime dataUltimo12Meses,
			LocalDateTime dataAtual, ProdutoEntity produto) {
		float mediaAvaliacao = avaliacaoService.getMediaAvaliacoesPorProduto(produto, dataUltimo12Meses, dataAtual);

		long quantidadeVendas = vendaRepository.countByProduto(produto);
		long quantidadeDias = ChronoUnit.DAYS.between(produto.getDataCriacao(), dataAtual);
		float mediaVendas = quantidadeDias > 0 ? (float) quantidadeVendas / quantidadeDias : 0;

		Long quantidadeNoticias = mapCategorias.get(produto.getCategoria().getId()).getQuantidadeNoticias();
		quantidadeNoticias = quantidadeNoticias != null ? quantidadeNoticias : 0;

		return mediaAvaliacao + mediaVendas + quantidadeNoticias;
	}

}
