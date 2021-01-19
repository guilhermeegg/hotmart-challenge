package com.hotmart.challenge.web.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotmart.challenge.domain.model.entity.ProdutoEntity;
import com.hotmart.challenge.service.ProdutoService;
import com.hotmart.challenge.web.dto.ProdutoRequestDTO;
import com.hotmart.challenge.web.dto.ProdutoResponseDTO;
import com.hotmart.challenge.web.transform.GenericModelMapper;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("challege-api/v1/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping("/{id}")
	@ApiOperation(value = "Obter Produto por ID", response = ProdutoResponseDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou Produto com sucesso"),
			@ApiResponse(code = 404, message = "Não encontrou o Produto com base no ID pesquisado") })
	public ResponseEntity<ProdutoResponseDTO> findById(@PathVariable("id") Long id) {
		Optional<ProdutoEntity> produto = produtoService.findById(id);
		if (produto.isPresent()) {
			return new ResponseEntity<>(GenericModelMapper.transform(produto.get(), ProdutoResponseDTO.class),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping
	@ApiOperation(value = "Cadastrar Produto", response = ProdutoResponseDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Produto cadastrado com sucesso") })
	public ResponseEntity<ProdutoResponseDTO> save(@Valid @RequestBody ProdutoRequestDTO produtoDTO,
			HttpServletResponse response) {
		ProdutoEntity produto = produtoService.save(GenericModelMapper.transform(produtoDTO, ProdutoEntity.class));
		return new ResponseEntity<>(GenericModelMapper.transform(produto, ProdutoResponseDTO.class),
				HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Remover Produto por ID", response = HttpStatus.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Exclusão do Produto realiazado com sucesso"),
			@ApiResponse(code = 404, message = "Não encontrou o Produto com base no ID pesquisado"),
			@ApiResponse(code = 412, message = "Pré condição falhou - Regra de negócio violada") })
	public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") Long id) {
		produtoService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
