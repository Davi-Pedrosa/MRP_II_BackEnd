package com.mrp2.backend.repository;

import com.mrp2.backend.model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
    List<Estoque> findByCategoria(String categoria);
    List<Estoque> findByStatus(Estoque.Status status);
    List<Estoque> findByQuantidadeLessThanEqual(Integer quantidade);
    List<Estoque> findByFornecedorId(Long fornecedorId);
} 