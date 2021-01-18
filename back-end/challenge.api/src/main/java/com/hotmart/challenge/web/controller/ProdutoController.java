package com.hotmart.challenge.web.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotmart.challenge.domain.model.entity.ProdutoEntity;
import com.hotmart.challenge.service.ProdutoService;
import com.hotmart.challenge.web.dto.ProdutoDTO;
import com.hotmart.challenge.web.transform.GenericModelMapper;

@RestController
@RequestMapping("/v1/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDTO> findById(@PathVariable("id") Long id) {
		Optional<ProdutoEntity> produto = produtoService.findById(id);
		if (produto.isPresent()) {
			return new ResponseEntity<>(GenericModelMapper.transform(produto.get(), ProdutoDTO.class), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
