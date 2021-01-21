package com.hotmart.challenge.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hotmart.challenge.domain.model.entity.AvaliacaoEntity;
import com.hotmart.challenge.domain.model.entity.ProdutoEntity;

@Repository
public interface AvaliacaoRepository extends JpaRepository<AvaliacaoEntity, Long> {

	@Query("select avaliacao from AvaliacaoEntity avaliacao where avaliacao.dataRegistro between :dataInicio and :dataFim and avaliacao.venda.produto = :produto")
	List<AvaliacaoEntity> findAllNoPeriodoPorProduto(@Param("produto") ProdutoEntity produto,
			@Param("dataInicio") LocalDateTime dataInicio, @Param("dataFim") LocalDateTime dataFim);

}
