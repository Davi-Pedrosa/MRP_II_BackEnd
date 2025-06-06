package com.mrp2.backend.repository;

import com.mrp2.backend.model.OrdemProducao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdemProducaoRepository extends JpaRepository<OrdemProducao, Long> {
    List<OrdemProducao> findAllByOrderByCreatedAtDesc();
    List<OrdemProducao> findByUsuarioId(Long usuarioId);
} 