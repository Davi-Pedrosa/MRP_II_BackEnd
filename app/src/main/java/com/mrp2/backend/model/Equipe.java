package com.mrp2.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "equipes")
public class Equipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String tipo;
    private Integer capacidadeDiaria;
    private Double emUso;
    private Integer cpusEmProcessamento;
    private Integer tempoPorUnidade;
    
    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Tendencia tendencia;

    @Column(name = "proxima_disponibilidade")
    private LocalDateTime proximaDisponibilidade;
    
    @Column(name = "ultima_atualizacao")
    private LocalDateTime ultimaAtualizacao;

    @OneToMany(mappedBy = "equipe", cascade = CascadeType.ALL)
    private List<MembroEquipe> membros;

    @ElementCollection
    private List<String> ferramentas;

    @ElementCollection
    private List<String> procedimentos;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public enum Status {
        NORMAL, ATENCAO, CRITICO
    }

    public enum Tendencia {
        SUBINDO, DESCENDO, ESTAVEL
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