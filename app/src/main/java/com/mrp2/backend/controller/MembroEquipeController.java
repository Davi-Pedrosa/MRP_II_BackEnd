package com.mrp2.backend.controller;

import com.mrp2.backend.model.MembroEquipe;
import com.mrp2.backend.service.MembroEquipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/membros")
@CrossOrigin(origins = "*")
public class MembroEquipeController {
    @Autowired
    private MembroEquipeService service;

    @PostMapping
    public ResponseEntity<MembroEquipe> criar(@RequestBody MembroEquipe membro) {
        return ResponseEntity.ok(service.save(membro));
    }

    @GetMapping
    public ResponseEntity<List<MembroEquipe>> listarTodos() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MembroEquipe> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/equipe/{equipeId}")
    public ResponseEntity<List<MembroEquipe>> buscarPorEquipe(@PathVariable Long equipeId) {
        return ResponseEntity.ok(service.findByEquipeId(equipeId));
    }

    @GetMapping("/disponivel/{disponivel}")
    public ResponseEntity<List<MembroEquipe>> buscarPorDisponibilidade(@PathVariable boolean disponivel) {
        return ResponseEntity.ok(service.findByDisponivel(disponivel));
    }

    @GetMapping("/funcao/{funcao}")
    public ResponseEntity<List<MembroEquipe>> buscarPorFuncao(@PathVariable String funcao) {
        return ResponseEntity.ok(service.findByFuncao(funcao));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<MembroEquipe>> buscarPorStatus(@PathVariable MembroEquipe.Status status) {
        return ResponseEntity.ok(service.findByStatus(status));
    }

    @GetMapping("/carga-trabalho/menor-que/{cargaMaxima}")
    public ResponseEntity<List<MembroEquipe>> buscarPorCargaTrabalhoMenorQue(@PathVariable Integer cargaMaxima) {
        return ResponseEntity.ok(service.findByCargaTrabalhoLessThan(cargaMaxima));
    }

    @PutMapping("/{id}/disponibilidade")
    public ResponseEntity<MembroEquipe> atualizarDisponibilidade(
            @PathVariable Long id,
            @RequestParam boolean disponivel) {
        return ResponseEntity.ok(service.atualizarDisponibilidade(id, disponivel));
    }

    @PutMapping("/{id}/ultima-atividade")
    public ResponseEntity<MembroEquipe> atualizarUltimaAtividade(
            @PathVariable Long id,
            @RequestBody String atividade) {
        return ResponseEntity.ok(service.atualizarUltimaAtividade(id, atividade));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<MembroEquipe> atualizarStatus(
            @PathVariable Long id,
            @RequestBody MembroEquipe.Status status) {
        return ResponseEntity.ok(service.atualizarStatus(id, status));
    }

    @PutMapping("/{id}/carga-trabalho")
    public ResponseEntity<MembroEquipe> atualizarCargaTrabalho(
            @PathVariable Long id,
            @RequestBody Integer cargaTrabalho) {
        return ResponseEntity.ok(service.atualizarCargaTrabalho(id, cargaTrabalho));
    }

    @PutMapping("/{id}/eficiencia")
    public ResponseEntity<MembroEquipe> atualizarEficiencia(
            @PathVariable Long id,
            @RequestBody Double eficiencia) {
        return ResponseEntity.ok(service.atualizarEficiencia(id, eficiencia));
    }

    @PutMapping("/{id}/habilidades")
    public ResponseEntity<MembroEquipe> atualizarHabilidades(
            @PathVariable Long id,
            @RequestBody List<String> habilidades) {
        return ResponseEntity.ok(service.atualizarHabilidades(id, habilidades));
    }

    @PutMapping("/{id}/certificacoes")
    public ResponseEntity<MembroEquipe> atualizarCertificacoes(
            @PathVariable Long id,
            @RequestBody List<String> certificacoes) {
        return ResponseEntity.ok(service.atualizarCertificacoes(id, certificacoes));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MembroEquipe> atualizar(
            @PathVariable Long id,
            @RequestBody MembroEquipe membro) {
        return ResponseEntity.ok(service.update(id, membro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
} 