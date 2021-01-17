package com.hotmart.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotmart.challenge.domain.model.entity.VendedorEntity;

@Repository
public interface VendedorRepository extends JpaRepository<VendedorEntity, Long> {

}
