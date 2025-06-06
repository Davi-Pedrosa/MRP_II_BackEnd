package com.mrp2.backend.controller;

import com.mrp2.backend.model.Equipe;
import com.mrp2.backend.service.EquipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/equipes")
@CrossOrigin(origins = "*")
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
} 