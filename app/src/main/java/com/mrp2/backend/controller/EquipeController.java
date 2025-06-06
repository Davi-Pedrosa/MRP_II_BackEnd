package com.mrp2.backend.controller;

import com.mrp2.backend.model.Equipe;
import com.mrp2.backend.model.MembroEquipe;
import com.mrp2.backend.service.EquipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/equipes")
public class EquipeController {
    @Autowired
    private EquipeService service;

    @PostMapping
    public ResponseEntity<Equipe> criar(@RequestBody Equipe equipe) {
        return ResponseEntity.ok(service.save(equipe));
    }

    @GetMapping
    public ResponseEntity<List<Equipe>> buscarTodas() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipe> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Equipe>> buscarPorStatus(@PathVariable Equipe.Status status) {
        return ResponseEntity.ok(service.findByStatus(status));
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Equipe>> buscarPorTipo(@PathVariable String tipo) {
        return ResponseEntity.ok(service.findByTipo(tipo));
    }

    @GetMapping("/uso-acima/{limite}")
    public ResponseEntity<List<Equipe>> buscarPorUsoAcima(@PathVariable Double limite) {
        return ResponseEntity.ok(service.findByEmUsoGreaterThan(limite));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipe> atualizar(@PathVariable Long id, @RequestBody Equipe equipe) {
        return ResponseEntity.ok(service.update(id, equipe));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Equipe> atualizarStatus(
            @PathVariable Long id,
            @RequestBody Equipe.Status status) {
        return ResponseEntity.ok(service.updateStatus(id, status));
    }

    @PutMapping("/{id}/capacidade")
    public ResponseEntity<Equipe> atualizarCapacidade(
            @PathVariable Long id,
            @RequestBody Integer capacidadeDiaria) {
        return ResponseEntity.ok(service.updateCapacidade(id, capacidadeDiaria));
    }

    @PutMapping("/{id}/uso")
    public ResponseEntity<Equipe> atualizarUso(
            @PathVariable Long id,
            @RequestBody Double emUso) {
        return ResponseEntity.ok(service.updateUso(id, emUso));
    }

    @PostMapping("/{id}/membros")
    public ResponseEntity<Equipe> adicionarMembro(
            @PathVariable Long id,
            @RequestBody MembroEquipe membro) {
        return ResponseEntity.ok(service.addMembro(id, membro));
    }

    @DeleteMapping("/{equipeId}/membros/{membroId}")
    public ResponseEntity<Void> removerMembro(
            @PathVariable Long equipeId,
            @PathVariable Long membroId) {
        service.removeMembro(equipeId, membroId);
        return ResponseEntity.ok().build();
    }
} 