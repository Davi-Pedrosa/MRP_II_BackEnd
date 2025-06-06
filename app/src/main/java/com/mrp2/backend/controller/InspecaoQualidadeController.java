package com.mrp2.backend.controller;

import com.mrp2.backend.model.InspecaoQualidade;
import com.mrp2.backend.service.InspecaoQualidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/inspecoes-qualidade")
@CrossOrigin(origins = "*")
public class InspecaoQualidadeController {
    @Autowired
    private InspecaoQualidadeService service;

    @PostMapping
    public ResponseEntity<InspecaoQualidade> criar(@RequestBody InspecaoQualidade inspecao) {
        return ResponseEntity.ok(service.save(inspecao));
    }

    @GetMapping
    public ResponseEntity<List<InspecaoQualidade>> buscarTodas() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InspecaoQualidade> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<InspecaoQualidade>> buscarPorStatus(@PathVariable InspecaoQualidade.Status status) {
        return ResponseEntity.ok(service.findByStatus(status));
    }

    @GetMapping("/produto/{produtoId}")
    public ResponseEntity<List<InspecaoQualidade>> buscarPorProduto(@PathVariable String produtoId) {
        return ResponseEntity.ok(service.findByProdutoId(produtoId));
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<InspecaoQualidade>> buscarPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {
        return ResponseEntity.ok(service.findByPeriodo(inicio, fim));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InspecaoQualidade> atualizar(@PathVariable Long id, @RequestBody InspecaoQualidade inspecao) {
        return ResponseEntity.ok(service.update(id, inspecao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
} 