package com.hotmart.challenge.web.dto;

import java.io.Serializable;

import org.springframework.data.domain.Page;

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
@ApiModel(description = "Dados do rank de produtos response")
public class ProdutoRankResponseDTO implements Serializable {

	private static final long serialVersionUID = 3414709162162820774L;

	@ApiModelProperty(position = 1, required = true, notes = "Data que foi realizada a consulta")
	private String dataAtual;

	@ApiModelProperty(position = 2, required = false, notes = "Termo utilizado na consulta")
	private String termoPesquisado;

	@ApiModelProperty(position = 3, required = true, notes = "Lista com o rank de produtos")
	private Page<ProdutoResponseDTO> produtos;

}
