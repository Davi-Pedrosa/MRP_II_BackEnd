package com.mrp2.backend.repository;

import com.mrp2.backend.model.InspecaoQualidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface InspecaoQualidadeRepository extends JpaRepository<InspecaoQualidade, Long> {
    List<InspecaoQualidade> findByStatus(InspecaoQualidade.Status status);
    List<InspecaoQualidade> findByProdutoId(String produtoId);
    List<InspecaoQualidade> findByNumeroLote(String numeroLote);
    List<InspecaoQualidade> findByInspecionadoPorId(Long id);
    List<InspecaoQualidade> findByDataInspecaoBetween(LocalDateTime start, LocalDateTime end);
} 