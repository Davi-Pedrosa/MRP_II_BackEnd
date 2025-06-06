package com.mrp2.backend.repository;

import com.mrp2.backend.model.ProductionOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductionOrderRepository extends JpaRepository<ProductionOrder, Long> {
    List<ProductionOrder> findByStatus(ProductionOrder.Status status);
    List<ProductionOrder> findByPriority(ProductionOrder.Priority priority);
    List<ProductionOrder> findByTeam(String team);
    List<ProductionOrder> findByStartDateBetween(LocalDateTime start, LocalDateTime end);
    List<ProductionOrder> findByEndDateBetween(LocalDateTime start, LocalDateTime end);
} 