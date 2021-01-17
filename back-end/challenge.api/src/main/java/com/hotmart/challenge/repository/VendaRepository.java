package com.hotmart.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotmart.challenge.domain.model.entity.VendaEntity;

@Repository
public interface VendaRepository extends JpaRepository<VendaEntity, Long> {

}
