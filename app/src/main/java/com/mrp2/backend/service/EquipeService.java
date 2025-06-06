package com.mrp2.backend.service;

import com.mrp2.backend.model.Equipe;
import com.mrp2.backend.repository.EquipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EquipeService {
    @Autowired
    private EquipeRepository repository;

    public Equipe save(Equipe equipe) {
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

    public void delete(Long id) {
        repository.deleteById(id);
    }

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
        existingEquipe.setUltimaAtualizacao(equipe.getUltimaAtualizacao());
        existingEquipe.setMembros(equipe.getMembros());
        existingEquipe.setFerramentas(equipe.getFerramentas());
        existingEquipe.setProcedimentos(equipe.getProcedimentos());

        return repository.save(existingEquipe);
    }
} 