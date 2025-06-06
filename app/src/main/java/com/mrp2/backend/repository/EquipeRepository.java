package com.mrp2.backend.repository;

import com.mrp2.backend.model.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe, Long> {
    List<Equipe> findByStatus(Equipe.Status status);
    List<Equipe> findByTipo(String tipo);
    List<Equipe> findByEmUsoGreaterThan(Double limite);
} 