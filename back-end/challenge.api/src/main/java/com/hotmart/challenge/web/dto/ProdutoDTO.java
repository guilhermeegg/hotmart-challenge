package com.hotmart.challenge.web.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ProdutoDTO implements Serializable {

	private static final long serialVersionUID = 4234351943267992177L;

	private Long id;

	private String nome;

	private String descricao;

	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime dataCriacao;

	private Float score;

}
