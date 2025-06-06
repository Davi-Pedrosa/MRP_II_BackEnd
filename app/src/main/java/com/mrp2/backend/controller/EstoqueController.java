package com.mrp2.backend.controller;

import com.mrp2.backend.model.Estoque;
import com.mrp2.backend.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/estoque")
@CrossOrigin(origins = "*")
public class EstoqueController {
    @Autowired
    private EstoqueService service;

    @PostMapping
    public ResponseEntity<Estoque> criar(@RequestBody Estoque item) {
        return ResponseEntity.ok(service.save(item));
    }

    @GetMapping
    public ResponseEntity<List<Estoque>> buscarTodos() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estoque> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Estoque>> buscarPorStatus(@PathVariable Estoque.Status status) {
        return ResponseEntity.ok(service.findByStatus(status));
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Estoque>> buscarPorCategoria(@PathVariable String categoria) {
        return ResponseEntity.ok(service.findByCategoria(categoria));
    }

    @GetMapping("/baixo-estoque")
    public ResponseEntity<List<Estoque>> buscarBaixoEstoque() {
        return ResponseEntity.ok(service.findLowStockItems());
    }

    @GetMapping("/fora-estoque")
    public ResponseEntity<List<Estoque>> buscarForaEstoque() {
        return ResponseEntity.ok(service.findOutOfStockItems());
    }

    @GetMapping("/quantidade/{quantidade}")
    public ResponseEntity<List<Estoque>> buscarPorQuantidadeMenorOuIgual(@PathVariable Integer quantidade) {
        return ResponseEntity.ok(service.findByQuantidadeMenorOuIgual(quantidade));
    }

    @GetMapping("/fornecedor/{fornecedorId}")
    public ResponseEntity<List<Estoque>> buscarPorFornecedor(@PathVariable Long fornecedorId) {
        return ResponseEntity.ok(service.findByFornecedorId(fornecedorId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estoque> atualizar(@PathVariable Long id, @RequestBody Estoque item) {
        return ResponseEntity.ok(service.update(id, item));
    }

    @PutMapping("/{id}/quantidade")
    public ResponseEntity<Estoque> atualizarQuantidade(
            @PathVariable Long id,
            @RequestBody AtualizacaoQuantidadeRequest request) {
        return ResponseEntity.ok(service.updateQuantity(id, request.getQuantidade()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}

class AtualizacaoQuantidadeRequest {
    private int quantidade;

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
} 