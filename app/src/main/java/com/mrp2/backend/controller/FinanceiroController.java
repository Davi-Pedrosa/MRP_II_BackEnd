package com.mrp2.backend.controller;

import com.mrp2.backend.model.Financeiro;
import com.mrp2.backend.service.FinanceiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/financeiro")
@CrossOrigin(origins = "*")
public class FinanceiroController {
    @Autowired
    private FinanceiroService service;

    @PostMapping
    public ResponseEntity<Financeiro> criar(@RequestBody Financeiro lancamento) {
        return ResponseEntity.ok(service.save(lancamento));
    }

    @GetMapping
    public ResponseEntity<List<Financeiro>> buscarTodos() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Financeiro> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/departamento/{departamento}")
    public ResponseEntity<List<Financeiro>> buscarPorDepartamento(@PathVariable String departamento) {
        return ResponseEntity.ok(service.findByDepartamento(departamento));
    }

    @GetMapping("/linha-produto/{linhaProduto}")
    public ResponseEntity<List<Financeiro>> buscarPorLinhaProduto(@PathVariable String linhaProduto) {
        return ResponseEntity.ok(service.findByLinhaProduto(linhaProduto));
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<Financeiro>> buscarPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {
        return ResponseEntity.ok(service.findByPeriodo(inicio, fim));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Financeiro> atualizar(@PathVariable Long id, @RequestBody Financeiro lancamento) {
        return ResponseEntity.ok(service.update(id, lancamento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
} 