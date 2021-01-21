package com.hotmart.challenge.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hotmart.challenge.domain.model.entity.ProdutoEntity;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

	@Query("select produto from ProdutoEntity produto order by produto.score desc, produto.nome asc, produto.categoria.nome asc")
	Page<ProdutoEntity> findAllOrderByRank(Pageable page);

}
