package com.mrp2.backend.service;

import com.mrp2.backend.model.InspecaoQualidade;
import com.mrp2.backend.repository.InspecaoQualidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class InspecaoQualidadeService {
    @Autowired
    private InspecaoQualidadeRepository repository;

    public InspecaoQualidade save(InspecaoQualidade inspecao) {
        if (inspecao.getDataInspecao() == null) {
            inspecao.setDataInspecao(LocalDateTime.now());
        }
        return repository.save(inspecao);
    }

    public List<InspecaoQualidade> findAll() {
        return repository.findAll();
    }

    public InspecaoQualidade findById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Inspeção de qualidade não encontrada com id: " + id));
    }

    public List<InspecaoQualidade> findByStatus(InspecaoQualidade.Status status) {
        return repository.findByStatus(status);
    }

    public List<InspecaoQualidade> findByProdutoId(String produtoId) {
        return repository.findByProdutoId(produtoId);
    }

    public List<InspecaoQualidade> findByNumeroLote(String numeroLote) {
        return repository.findByNumeroLote(numeroLote);
    }

    public List<InspecaoQualidade> findByInspecionadoPorId(Long userId) {
        return repository.findByInspecionadoPorId(userId);
    }

    public List<InspecaoQualidade> findByPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return repository.findByDataInspecaoBetween(inicio, fim);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public InspecaoQualidade update(Long id, InspecaoQualidade inspecao) {
        InspecaoQualidade existingInspecao = findById(id);
        
        // Atualizar campos
        existingInspecao.setProdutoId(inspecao.getProdutoId());
        existingInspecao.setNumeroLote(inspecao.getNumeroLote());
        existingInspecao.setInspecionadoPor(inspecao.getInspecionadoPor());
        existingInspecao.setParametros(inspecao.getParametros());
        existingInspecao.setObservacoes(inspecao.getObservacoes());
        existingInspecao.setStatus(inspecao.getStatus());
        existingInspecao.setAnexos(inspecao.getAnexos());

        return repository.save(existingInspecao);
    }

    public InspecaoQualidade updateStatus(Long id, InspecaoQualidade.Status status) {
        InspecaoQualidade inspecao = findById(id);
        inspecao.setStatus(status);
        return repository.save(inspecao);
    }

    public InspecaoQualidade addParametro(Long id, InspecaoQualidade.ParametroInspecao parametro) {
        InspecaoQualidade inspecao = findById(id);
        inspecao.getParametros().add(parametro);
        return repository.save(inspecao);
    }

    public InspecaoQualidade addAnexo(Long id, InspecaoQualidade.Anexo anexo) {
        InspecaoQualidade inspecao = findById(id);
        inspecao.getAnexos().add(anexo);
        return repository.save(inspecao);
    }
} 