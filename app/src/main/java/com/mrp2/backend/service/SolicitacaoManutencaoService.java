package com.mrp2.backend.service;

import com.mrp2.backend.model.SolicitacaoManutencao;
import com.mrp2.backend.repository.SolicitacaoManutencaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SolicitacaoManutencaoService {
    @Autowired
    private SolicitacaoManutencaoRepository repository;

    public SolicitacaoManutencao save(SolicitacaoManutencao solicitacao) {
        if (solicitacao.getDataSolicitacao() == null) {
            solicitacao.setDataSolicitacao(LocalDateTime.now());
        }
        return repository.save(solicitacao);
    }

    public List<SolicitacaoManutencao> findAll() {
        return repository.findAll();
    }

    public SolicitacaoManutencao findById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Solicitação de manutenção não encontrada com id: " + id));
    }

    public List<SolicitacaoManutencao> findByStatus(SolicitacaoManutencao.Status status) {
        return repository.findByStatus(status);
    }

    public List<SolicitacaoManutencao> findByPrioridade(SolicitacaoManutencao.Prioridade prioridade) {
        return repository.findByPrioridade(prioridade);
    }

    public List<SolicitacaoManutencao> findBySolicitadoPorId(Long userId) {
        return repository.findBySolicitadoPorId(userId);
    }

    public List<SolicitacaoManutencao> findByAtribuidoParaId(Long userId) {
        return repository.findByAtribuidoParaId(userId);
    }

    public List<SolicitacaoManutencao> findByEquipamento(String equipamento) {
        return repository.findByEquipamento(equipamento);
    }

    public List<SolicitacaoManutencao> findByPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return repository.findByDataSolicitacaoBetween(inicio, fim);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public SolicitacaoManutencao update(Long id, SolicitacaoManutencao solicitacao) {
        SolicitacaoManutencao existingSolicitacao = findById(id);
        
        // Atualizar campos
        existingSolicitacao.setEquipamento(solicitacao.getEquipamento());
        existingSolicitacao.setDescricao(solicitacao.getDescricao());
        existingSolicitacao.setPrioridade(solicitacao.getPrioridade());
        existingSolicitacao.setStatus(solicitacao.getStatus());
        existingSolicitacao.setSolicitadoPor(solicitacao.getSolicitadoPor());
        existingSolicitacao.setAtribuidoPara(solicitacao.getAtribuidoPara());
        existingSolicitacao.setDepartamento(solicitacao.getDepartamento());
        existingSolicitacao.setDataConclusao(solicitacao.getDataConclusao());
        existingSolicitacao.setObservacoes(solicitacao.getObservacoes());

        return repository.save(existingSolicitacao);
    }
} 