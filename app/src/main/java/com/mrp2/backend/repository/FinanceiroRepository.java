package com.mrp2.backend.repository;

import com.mrp2.backend.model.Financeiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FinanceiroRepository extends JpaRepository<Financeiro, Long> {
    List<Financeiro> findByDataBetween(LocalDateTime inicio, LocalDateTime fim);
    List<Financeiro> findByDepartamento(String departamento);
    List<Financeiro> findByLinhaProduto(String linhaProduto);
} 