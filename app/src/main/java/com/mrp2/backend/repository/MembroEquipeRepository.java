package com.mrp2.backend.repository;

import com.mrp2.backend.model.MembroEquipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MembroEquipeRepository extends JpaRepository<MembroEquipe, Long> {
    List<MembroEquipe> findByEquipeId(Long equipeId);
    List<MembroEquipe> findByDisponivel(boolean disponivel);
    List<MembroEquipe> findByFuncao(String funcao);
    List<MembroEquipe> findByStatus(MembroEquipe.Status status);
    List<MembroEquipe> findByCargaTrabalhoLessThan(Integer cargaMaxima);
} 