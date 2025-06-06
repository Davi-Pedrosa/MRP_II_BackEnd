package com.mrp2.backend.service;

import com.mrp2.backend.model.ProductionOrder;
import com.mrp2.backend.repository.ProductionOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductionOrderService {
    @Autowired
    private ProductionOrderRepository repository;

    public List<ProductionOrder> findAll() {
        return repository.findAll();
    }

    public ProductionOrder findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ordem de produção não encontrada"));
    }

    public List<ProductionOrder> findByStatus(ProductionOrder.Status status) {
        return repository.findByStatus(status);
    }

    public List<ProductionOrder> findByPriority(ProductionOrder.Priority priority) {
        return repository.findByPriority(priority);
    }

    public List<ProductionOrder> findByTeam(String team) {
        return repository.findByTeam(team);
    }

    public List<ProductionOrder> findByStartDateBetween(LocalDateTime start, LocalDateTime end) {
        return repository.findByStartDateBetween(start, end);
    }

    public List<ProductionOrder> findByEndDateBetween(LocalDateTime start, LocalDateTime end) {
        return repository.findByEndDateBetween(start, end);
    }

    public ProductionOrder save(ProductionOrder order) {
        return repository.save(order);
    }

    public ProductionOrder update(Long id, ProductionOrder order) {
        ProductionOrder existingOrder = findById(id);
        order.setId(existingOrder.getId());
        order.setCreatedAt(existingOrder.getCreatedAt());
        return repository.save(order);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public ProductionOrder updateStatus(Long id, ProductionOrder.Status status) {
        ProductionOrder order = findById(id);
        order.setStatus(status);
        return repository.save(order);
    }
} 