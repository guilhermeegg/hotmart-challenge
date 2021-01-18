package com.hotmart.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotmart.challenge.domain.model.entity.AvaliacaoEntity;

@Repository
public interface AvaliacaoRepository extends JpaRepository<AvaliacaoEntity, Long> {

}
