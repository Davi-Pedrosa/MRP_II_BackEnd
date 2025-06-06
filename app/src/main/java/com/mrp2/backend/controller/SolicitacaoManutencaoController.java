package com.mrp2.backend.controller;

import com.mrp2.backend.model.SolicitacaoManutencao;
import com.mrp2.backend.service.SolicitacaoManutencaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/solicitacoes-manutencao")
@CrossOrigin(origins = "*")
public class SolicitacaoManutencaoController {
    @Autowired
    private SolicitacaoManutencaoService service;

    @PostMapping
    public ResponseEntity<SolicitacaoManutencao> criar(@RequestBody SolicitacaoManutencao solicitacao) {
        return ResponseEntity.ok(service.save(solicitacao));
    }

    @GetMapping
    public ResponseEntity<List<SolicitacaoManutencao>> buscarTodas() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitacaoManutencao> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<SolicitacaoManutencao>> buscarPorStatus(@PathVariable SolicitacaoManutencao.Status status) {
        return ResponseEntity.ok(service.findByStatus(status));
    }

    @GetMapping("/prioridade/{prioridade}")
    public ResponseEntity<List<SolicitacaoManutencao>> buscarPorPrioridade(@PathVariable SolicitacaoManutencao.Prioridade prioridade) {
        return ResponseEntity.ok(service.findByPrioridade(prioridade));
    }

    @GetMapping("/solicitado-por/{userId}")
    public ResponseEntity<List<SolicitacaoManutencao>> buscarPorSolicitante(@PathVariable Long userId) {
        return ResponseEntity.ok(service.findBySolicitadoPorId(userId));
    }

    @GetMapping("/atribuido-para/{userId}")
    public ResponseEntity<List<SolicitacaoManutencao>> buscarPorResponsavel(@PathVariable Long userId) {
        return ResponseEntity.ok(service.findByAtribuidoParaId(userId));
    }

    @GetMapping("/equipamento/{equipamento}")
    public ResponseEntity<List<SolicitacaoManutencao>> buscarPorEquipamento(@PathVariable String equipamento) {
        return ResponseEntity.ok(service.findByEquipamento(equipamento));
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<SolicitacaoManutencao>> buscarPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {
        return ResponseEntity.ok(service.findByPeriodo(inicio, fim));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolicitacaoManutencao> atualizar(@PathVariable Long id, @RequestBody SolicitacaoManutencao solicitacao) {
        return ResponseEntity.ok(service.update(id, solicitacao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
} 