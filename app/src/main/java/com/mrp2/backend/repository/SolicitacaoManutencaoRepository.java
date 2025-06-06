package com.mrp2.backend.repository;

import com.mrp2.backend.model.SolicitacaoManutencao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SolicitacaoManutencaoRepository extends JpaRepository<SolicitacaoManutencao, Long> {
    List<SolicitacaoManutencao> findByStatus(SolicitacaoManutencao.Status status);
    List<SolicitacaoManutencao> findByPrioridade(SolicitacaoManutencao.Prioridade prioridade);
    List<SolicitacaoManutencao> findBySolicitadoPorId(Long solicitadoPorId);
    List<SolicitacaoManutencao> findByAtribuidoParaId(Long atribuidoParaId);
    List<SolicitacaoManutencao> findByEquipamento(String equipamento);
    List<SolicitacaoManutencao> findByDataSolicitacaoBetween(LocalDateTime inicio, LocalDateTime fim);
} 