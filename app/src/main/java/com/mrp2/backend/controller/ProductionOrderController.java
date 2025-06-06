package com.mrp2.backend.controller;

import com.mrp2.backend.model.ProductionOrder;
import com.mrp2.backend.service.ProductionOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/production-orders")
@CrossOrigin(origins = "*")
public class ProductionOrderController {
    @Autowired
    private ProductionOrderService service;

    @GetMapping
    public ResponseEntity<List<ProductionOrder>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductionOrder> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ProductionOrder>> findByStatus(@PathVariable ProductionOrder.Status status) {
        return ResponseEntity.ok(service.findByStatus(status));
    }

    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<ProductionOrder>> findByPriority(@PathVariable ProductionOrder.Priority priority) {
        return ResponseEntity.ok(service.findByPriority(priority));
    }

    @GetMapping("/team/{team}")
    public ResponseEntity<List<ProductionOrder>> findByTeam(@PathVariable String team) {
        return ResponseEntity.ok(service.findByTeam(team));
    }

    @GetMapping("/start-date-range")
    public ResponseEntity<List<ProductionOrder>> findByStartDateBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(service.findByStartDateBetween(start, end));
    }

    @GetMapping("/end-date-range")
    public ResponseEntity<List<ProductionOrder>> findByEndDateBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(service.findByEndDateBetween(start, end));
    }

    @PostMapping
    public ResponseEntity<ProductionOrder> create(@RequestBody ProductionOrder order) {
        return ResponseEntity.ok(service.save(order));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductionOrder> update(@PathVariable Long id, @RequestBody ProductionOrder order) {
        return ResponseEntity.ok(service.update(id, order));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/status/{status}")
    public ResponseEntity<ProductionOrder> updateStatus(@PathVariable Long id, @PathVariable ProductionOrder.Status status) {
        return ResponseEntity.ok(service.updateStatus(id, status));
    }
} 