package com.hotmart.challenge.domain.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TBEXECUCAO_SERVICO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ExecucaoServicoEntity implements Serializable {

	private static final long serialVersionUID = -1592913580228703107L;

	@Id
	@Column(name = "ID")
	private Long id;

	@NotNull
	@Size(max = 100)
	@Column(name = "NOME_SERVICO")
	private String nomeServico;

	@Column(name = "DT_ULTIMA_EXECUCAO")
	private LocalDateTime dataUltimaExecucao;

}
