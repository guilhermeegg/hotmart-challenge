package com.hotmart.challenge.web.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@ApiModel(description = "Dados do produto request")
public class ProdutoRequestDTO implements Serializable {

	private static final long serialVersionUID = 7542935884330422860L;

	@ApiModelProperty(position = 1, required = true, notes = "Identificação do produto")
	private Long id;

	@ApiModelProperty(position = 2, required = true, notes = "Nome do produto")
	private String nome;

	@ApiModelProperty(position = 3, required = true, notes = "Descrição do produto")
	private String descricao;

	@ApiModelProperty(position = 4, required = true, notes = "Data da criação do produto - yyyy-mm-dd HH:mm:ss")
	private LocalDateTime dataCriacao;

}
