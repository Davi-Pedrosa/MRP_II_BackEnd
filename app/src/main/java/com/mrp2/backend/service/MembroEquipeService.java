package com.mrp2.backend.service;

import com.mrp2.backend.model.Equipe;
import com.mrp2.backend.model.MembroEquipe;
import com.mrp2.backend.repository.MembroEquipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MembroEquipeService {
    @Autowired
    private MembroEquipeRepository repository;

    public MembroEquipe save(MembroEquipe membro) {
        return repository.save(membro);
    }

    public List<MembroEquipe> findAll() {
        return repository.findAll();
    }

    public MembroEquipe findById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Membro não encontrado com id: " + id));
    }

    public List<MembroEquipe> findByEquipeId(Long equipeId) {
        return repository.findByEquipeId(equipeId);
    }

    public List<MembroEquipe> findByDisponivel(boolean disponivel) {
        return repository.findByDisponivel(disponivel);
    }

    public List<MembroEquipe> findByFuncao(String funcao) {
        return repository.findByFuncao(funcao);
    }

    public List<MembroEquipe> findByStatus(MembroEquipe.Status status) {
        return repository.findByStatus(status);
    }

    public List<MembroEquipe> findByCargaTrabalhoLessThan(Integer cargaMaxima) {
        return repository.findByCargaTrabalhoLessThan(cargaMaxima);
    }

    public MembroEquipe atualizarDisponibilidade(Long id, boolean disponivel) {
        MembroEquipe membro = findById(id);
        membro.setDisponivel(disponivel);
        membro.setUltimaAtividade(disponivel ? "Disponível" : "Ocupado");
        membro.setDataUltimaAtividade(LocalDateTime.now());
        return repository.save(membro);
    }

    public MembroEquipe atualizarUltimaAtividade(Long id, String atividade) {
        MembroEquipe membro = findById(id);
        membro.setUltimaAtividade(atividade);
        membro.setDataUltimaAtividade(LocalDateTime.now());
        return repository.save(membro);
    }

    public MembroEquipe atualizarEquipe(Long id, Equipe equipe) {
        MembroEquipe membro = findById(id);
        membro.setEquipe(equipe);
        return repository.save(membro);
    }

    public MembroEquipe atualizarHabilidades(Long id, List<String> habilidades) {
        MembroEquipe membro = findById(id);
        membro.setHabilidades(habilidades);
        return repository.save(membro);
    }

    public MembroEquipe atualizarCertificacoes(Long id, List<String> certificacoes) {
        MembroEquipe membro = findById(id);
        membro.setCertificacoes(certificacoes);
        return repository.save(membro);
    }

    public MembroEquipe atualizarStatus(Long id, MembroEquipe.Status status) {
        MembroEquipe membro = findById(id);
        membro.setStatus(status);
        return repository.save(membro);
    }

    public MembroEquipe atualizarCargaTrabalho(Long id, Integer cargaTrabalho) {
        MembroEquipe membro = findById(id);
        membro.setCargaTrabalho(cargaTrabalho);
        return repository.save(membro);
    }

    public MembroEquipe atualizarEficiencia(Long id, Double eficiencia) {
        MembroEquipe membro = findById(id);
        membro.setEficiencia(eficiencia);
        return repository.save(membro);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public MembroEquipe update(Long id, MembroEquipe membro) {
        MembroEquipe membroExistente = findById(id);
        
        // Atualizar campos básicos
        membroExistente.setNome(membro.getNome());
        membroExistente.setFuncao(membro.getFuncao());
        membroExistente.setDisponivel(membro.isDisponivel());
        membroExistente.setUltimaAtividade(membro.getUltimaAtividade());
        membroExistente.setDataUltimaAtividade(LocalDateTime.now());
        
        // Atualizar campos de performance
        membroExistente.setStatus(membro.getStatus());
        membroExistente.setCargaTrabalho(membro.getCargaTrabalho());
        membroExistente.setEficiencia(membro.getEficiencia());
        
        // Atualizar relacionamentos
        membroExistente.setEquipe(membro.getEquipe());
        membroExistente.setHabilidades(membro.getHabilidades());
        membroExistente.setCertificacoes(membro.getCertificacoes());
        
        // Atualizar observações
        membroExistente.setObservacoes(membro.getObservacoes());

        return repository.save(membroExistente);
    }
} 