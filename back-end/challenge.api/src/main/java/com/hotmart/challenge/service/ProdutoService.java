package com.hotmart.challenge.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hotmart.challenge.domain.model.entity.ProdutoEntity;
import com.hotmart.challenge.repository.ProdutoRepository;
import com.hotmart.challenge.util.MessageUtil;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	@Autowired
	private MessageUtil messageUtil;

	public ProdutoEntity findById(Long id) {
		Optional<ProdutoEntity> produto = repository.findById(id);
		if (!produto.isPresent()) {
			throw new EntityNotFoundException(messageUtil.getMessage(MessageUtil.PRODUTO_NAO_ENCONTRADO));
		}
		return produto.get();
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	public ProdutoEntity save(ProdutoEntity produto) {
		return repository.save(produto);
	}

	public ProdutoEntity update(Long id, ProdutoEntity produtoEdit) {
		ProdutoEntity produto = findById(id);
		BeanUtils.copyProperties(produtoEdit, produto, "id", "score");
		return repository.save(produto);
	}

	public Page<ProdutoEntity> findAll(Pageable page) {
		return repository.findAll(page);
	}

}
