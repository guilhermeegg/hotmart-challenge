package com.hotmart.challenge.web.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotmart.challenge.domain.model.entity.ProdutoEntity;
import com.hotmart.challenge.service.ProdutoService;
import com.hotmart.challenge.util.MessageUtil;
import com.hotmart.challenge.web.dto.ProdutoRequestDTO;
import com.hotmart.challenge.web.dto.ProdutoResponseDTO;
import com.hotmart.challenge.web.transform.GenericModelMapper;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("challege-api/v1/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private MessageUtil messageUtil;

	@GetMapping("/{id}")
	@ApiOperation(value = "Obter Produto por ID", response = ProdutoResponseDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou Produto com sucesso"),
			@ApiResponse(code = 404, message = "Não encontrou o Produto com base no ID pesquisado") })
	public ResponseEntity<ProdutoResponseDTO> findById(@PathVariable("id") Long id) {
		ProdutoEntity produto = produtoService.findById(id);
		return new ResponseEntity<>(GenericModelMapper.transform(produto, ProdutoResponseDTO.class), HttpStatus.OK);
	}

	@GetMapping("/")
	@ApiOperation(value = "Obter produtos")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou os Produtos") })
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", dataType = "string", paramType = "query", value = "Página de resultados que você deseja recuperar (0..N). Página padrão 0"),
			@ApiImplicitParam(name = "size", dataType = "string", paramType = "query", value = "Número de registros por página. Tamanho da página padrão 10"),
			@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Critérios de ordenação no formato: propriedade (asc | desc). A ordem de classificação padrão está asc.") })
	public ResponseEntity<Page<ProdutoResponseDTO>> findAll(@ApiIgnore @PageableDefault Pageable page) {
		if (page.getPageSize() > 20) {
			throw new IllegalArgumentException(messageUtil.getMessage(MessageUtil.TAMANHO_LIMITE_PAGINACAO));
		}
		Page<ProdutoResponseDTO> produtos = produtoService.findAll(page)
				.map(produto -> GenericModelMapper.transform(produto, ProdutoResponseDTO.class));
		return new ResponseEntity<>(produtos, HttpStatus.OK);
	}

	@PostMapping
	@ApiOperation(value = "Cadastrar Produto", response = ProdutoResponseDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Produto cadastrado com sucesso"),
			@ApiResponse(code = 404, message = "Não encontrou a Categoria na base de dados") })
	public ResponseEntity<ProdutoResponseDTO> save(@Valid @RequestBody ProdutoRequestDTO produtoDTO,
			HttpServletResponse response) {
		ProdutoEntity produto = produtoService.save(GenericModelMapper.transform(produtoDTO, ProdutoEntity.class));
		return new ResponseEntity<>(GenericModelMapper.transform(produto, ProdutoResponseDTO.class),
				HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Editar Produto", response = ProdutoResponseDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Produto editado com sucesso") })
	public ResponseEntity<ProdutoResponseDTO> update(@PathVariable Long id,
			@Valid @RequestBody ProdutoRequestDTO produtoDTO) {
		ProdutoEntity produto = produtoService.update(id,
				GenericModelMapper.transform(produtoDTO, ProdutoEntity.class));
		return new ResponseEntity<>(GenericModelMapper.transform(produto, ProdutoResponseDTO.class), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Remover Produto por ID", response = HttpStatus.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Exclusão do Produto realiazado com sucesso"),
			@ApiResponse(code = 404, message = "Não encontrou o Produto na base de dados"),
			@ApiResponse(code = 412, message = "Pré condição falhou - Regra de negócio violada") })
	public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") Long id) {
		produtoService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
