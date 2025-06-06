package com.mrp2.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ordens_producao")
public class OrdemProducao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String numeroPedido;

    @Column(nullable = false)
    private String produto;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(nullable = false)
    private LocalDateTime dataPrevisao;

    @Column(nullable = false, length = 1000)
    private String descricao;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;

    @Column(length = 1000)
    private String observacoes;

    @Column(nullable = false)
    private Double custoEstimado;

    @Column(nullable = false)
    private Integer tempoEstimado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public enum Status {
        PENDENTE,
        EM_PRODUCAO,
        CONCLUIDA,
        CANCELADA
    }

    public enum Prioridade {
        BAIXA,
        NORMAL,
        ALTA,
        URGENTE
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
} 