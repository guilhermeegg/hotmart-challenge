package com.hotmart.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotmart.challenge.domain.model.entity.ExecucaoServicoEntity;

@Repository
public interface ExecucaoServicoRepository extends JpaRepository<ExecucaoServicoEntity, Long> {

}