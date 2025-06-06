package com.mrp2.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "estoque")
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String categoria;
    private Integer quantidade;
    
    @Column(name = "quantidade_minima")
    private Integer quantidadeMinima;
    
    @Column(name = "quantidade_maxima")
    private Integer quantidadeMaxima;
    
    private String unidade;
    private String localizacao;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public enum Status {
        DISPONIVEL, BAIXO_ESTOQUE, FORA_ESTOQUE
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        updateStatus();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
        updateStatus();
    }

    private void updateStatus() {
        if (quantidade <= 0) {
            status = Status.FORA_ESTOQUE;
        } else if (quantidade < quantidadeMinima) {
            status = Status.BAIXO_ESTOQUE;
        } else {
            status = Status.DISPONIVEL;
        }
    }
} 