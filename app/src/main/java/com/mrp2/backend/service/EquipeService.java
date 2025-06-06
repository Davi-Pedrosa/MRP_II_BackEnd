package com.mrp2.backend.service;

import com.mrp2.backend.model.Equipe;
import com.mrp2.backend.model.MembroEquipe;
import com.mrp2.backend.repository.EquipeRepository;
import com.mrp2.backend.repository.MembroEquipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EquipeService {
    @Autowired
    private EquipeRepository repository;

    @Autowired
    private MembroEquipeRepository membroRepository;

    @Transactional
    public Equipe save(Equipe equipe) {
        equipe.setUltimaAtualizacao(LocalDateTime.now());
        return repository.save(equipe);
    }

    public List<Equipe> findAll() {
        return repository.findAll();
    }

    public Equipe findById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Equipe não encontrada com id: " + id));
    }

    public List<Equipe> findByStatus(Equipe.Status status) {
        return repository.findByStatus(status);
    }

    public List<Equipe> findByTipo(String tipo) {
        return repository.findByTipo(tipo);
    }

    public List<Equipe> findByEmUsoGreaterThan(Double limite) {
        return repository.findByEmUsoGreaterThan(limite);
    }

    @Transactional
    public void delete(Long id) {
        Equipe equipe = findById(id);
        
        // Primeiro, excluir todos os membros da equipe
        if (equipe.getMembros() != null) {
            membroRepository.deleteAll(equipe.getMembros());
        }
        
        // Depois, excluir a equipe
        repository.delete(equipe);
    }

    @Transactional
    public Equipe update(Long id, Equipe equipe) {
        Equipe existingEquipe = findById(id);
        
        // Atualizar campos
        existingEquipe.setNome(equipe.getNome());
        existingEquipe.setTipo(equipe.getTipo());
        existingEquipe.setCapacidadeDiaria(equipe.getCapacidadeDiaria());
        existingEquipe.setEmUso(equipe.getEmUso());
        existingEquipe.setCpusEmProcessamento(equipe.getCpusEmProcessamento());
        existingEquipe.setTempoPorUnidade(equipe.getTempoPorUnidade());
        existingEquipe.setStatus(equipe.getStatus());
        existingEquipe.setTendencia(equipe.getTendencia());
        existingEquipe.setProximaDisponibilidade(equipe.getProximaDisponibilidade());
        existingEquipe.setUltimaAtualizacao(LocalDateTime.now());
        existingEquipe.setFerramentas(equipe.getFerramentas());
        existingEquipe.setProcedimentos(equipe.getProcedimentos());

        return repository.save(existingEquipe);
    }

    @Transactional
    public Equipe updateStatus(Long id, Equipe.Status status) {
        Equipe equipe = findById(id);
        equipe.setStatus(status);
        equipe.setUltimaAtualizacao(LocalDateTime.now());
        return repository.save(equipe);
    }

    @Transactional
    public Equipe updateCapacidade(Long id, Integer capacidadeDiaria) {
        Equipe equipe = findById(id);
        equipe.setCapacidadeDiaria(capacidadeDiaria);
        equipe.setUltimaAtualizacao(LocalDateTime.now());
        return repository.save(equipe);
    }

    @Transactional
    public Equipe updateUso(Long id, Double emUso) {
        Equipe equipe = findById(id);
        equipe.setEmUso(emUso);
        equipe.setUltimaAtualizacao(LocalDateTime.now());
        return repository.save(equipe);
    }

    @Transactional
    public Equipe addMembro(Long equipeId, MembroEquipe membro) {
        Equipe equipe = findById(equipeId);
        membro.setEquipe(equipe);
        membroRepository.save(membro);
        equipe.getMembros().add(membro);
        equipe.setUltimaAtualizacao(LocalDateTime.now());
        return repository.save(equipe);
    }

    @Transactional
    public void removeMembro(Long equipeId, Long membroId) {
        Equipe equipe = findById(equipeId);
        MembroEquipe membro = membroRepository.findById(membroId)
            .orElseThrow(() -> new RuntimeException("Membro não encontrado com id: " + membroId));
        
        if (!membro.getEquipe().getId().equals(equipeId)) {
            throw new RuntimeException("Membro não pertence a esta equipe");
        }

        equipe.getMembros().remove(membro);
        membroRepository.delete(membro);
        equipe.setUltimaAtualizacao(LocalDateTime.now());
        repository.save(equipe);
    }
} 