package com.hotmart.challenge.web.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotmart.challenge.domain.model.entity.ProdutoEntity;
import com.hotmart.challenge.service.ProdutoService;
import com.hotmart.challenge.util.MessageUtil;
import com.hotmart.challenge.web.dto.ProdutoRankResponseDTO;
import com.hotmart.challenge.web.dto.ProdutoRequestDTO;
import com.hotmart.challenge.web.dto.ProdutoResponseDTO;
import com.hotmart.challenge.web.transform.GenericModelMapper;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("challege-api/v1/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private MessageUtil messageUtil;

	@GetMapping("/{id}")
	@ApiOperation(value = "Obter produto por ID", response = ProdutoResponseDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou produto com sucesso"),
			@ApiResponse(code = 404, message = "Não encontrou o produto com base no ID pesquisado") })
	public ResponseEntity<ProdutoResponseDTO> findById(@PathVariable("id") Long id) {
		ProdutoEntity produto = produtoService.findById(id);
		return new ResponseEntity<>(GenericModelMapper.transform(produto, ProdutoResponseDTO.class), HttpStatus.OK);
	}

	@GetMapping("/")
	@ApiOperation(value = "Obter produtos")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou os produtos"),
			@ApiResponse(code = 404, message = "Não encontrou os produtos"),
			@ApiResponse(code = 400, message = "Erro na requisição do cliente") })
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", dataType = "string", paramType = "query", value = "Página de resultados que você deseja recuperar (0..N). Valor default 0"),
			@ApiImplicitParam(name = "size", dataType = "string", paramType = "query", value = "Número de registros por página. Tamanho máximo de 20. Valor default 10") })
	public ResponseEntity<Page<ProdutoResponseDTO>> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size) {
		validarTramanhoMaximoPagina(size);
		Page<ProdutoResponseDTO> produtos = produtoService.findAll(PageRequest.of(page, size))
				.map(produto -> GenericModelMapper.transform(produto, ProdutoResponseDTO.class));
		return produtos.getTotalElements() > 0 ? ResponseEntity.ok(produtos) : ResponseEntity.notFound().build();
	}

	@GetMapping("/rank")
	@ApiOperation(value = "Obter produtos ordenados pelo ranqueamento", response = ProdutoRankResponseDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou os produtos"),
			@ApiResponse(code = 404, message = "Não encontrou os produtos"),
			@ApiResponse(code = 400, message = "Erro na requisição do cliente") })
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", dataType = "string", paramType = "query", value = "Página de resultados que você deseja recuperar (0..N). Valor default 0"),
			@ApiImplicitParam(name = "size", dataType = "string", paramType = "query", value = "Número de registros por página. Tamanho máximo de 20. Valor default 10") })
	public ResponseEntity<ProdutoRankResponseDTO> findAllOrderByRank(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size) {
		validarTramanhoMaximoPagina(size);
		Page<ProdutoResponseDTO> produtos = produtoService.findAllOrderByRank(PageRequest.of(page, size))
				.map(produto -> GenericModelMapper.transform(produto, ProdutoResponseDTO.class));
		ProdutoRankResponseDTO response = new ProdutoRankResponseDTO(getDataAtual(), null, produtos);
		return response.getProdutos().getTotalElements() > 0 ? ResponseEntity.ok(response)
				: ResponseEntity.notFound().build();
	}

	@PostMapping
	@ApiOperation(value = "Cadastrar produto", response = ProdutoResponseDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Produto cadastrado com sucesso"),
			@ApiResponse(code = 404, message = "Não encontrou a categoria na base de dados") })
	public ResponseEntity<ProdutoResponseDTO> save(@Valid @RequestBody ProdutoRequestDTO produtoDTO,
			HttpServletResponse response) {
		ProdutoEntity produto = produtoService.save(GenericModelMapper.transform(produtoDTO, ProdutoEntity.class));
		return new ResponseEntity<>(GenericModelMapper.transform(produto, ProdutoResponseDTO.class),
				HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Editar produto", response = ProdutoResponseDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Produto editado com sucesso") })
	public ResponseEntity<ProdutoResponseDTO> update(@PathVariable Long id,
			@Valid @RequestBody ProdutoRequestDTO produtoDTO) {
		ProdutoEntity produto = produtoService.update(id,
				GenericModelMapper.transform(produtoDTO, ProdutoEntity.class));
		return new ResponseEntity<>(GenericModelMapper.transform(produto, ProdutoResponseDTO.class), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Remover produto por ID", response = HttpStatus.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Exclusão do produto realiazado com sucesso"),
			@ApiResponse(code = 404, message = "Não encontrou o produto na base de dados"),
			@ApiResponse(code = 412, message = "Pré condição falhou - Regra de negócio violada") })
	public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") Long id) {
		produtoService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Valida o tamanho máximo permitido para a página
	 * 
	 * @param size
	 * @throws IllegalArgumentException
	 */
	private void validarTramanhoMaximoPagina(int size) throws IllegalArgumentException {
		if (size > 20) {
			throw new IllegalArgumentException(messageUtil.getMessage(MessageUtil.TAMANHO_LIMITE_PAGINACAO));
		}
	}

	private String getDataAtual() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		return LocalDateTime.now().format(formatter);
	}

}
