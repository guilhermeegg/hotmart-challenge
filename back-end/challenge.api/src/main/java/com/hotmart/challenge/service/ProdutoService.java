package com.hotmart.challenge.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotmart.challenge.domain.model.entity.ProdutoEntity;
import com.hotmart.challenge.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	public Optional<ProdutoEntity> findById(Long id) {
		return repository.findById(id);
	}
	
	

}
