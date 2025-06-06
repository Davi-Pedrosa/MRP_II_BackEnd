package com.mrp2.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "membros_equipe")
public class MembroEquipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String funcao;

    private boolean disponivel;
    
    @Column(name = "ultima_atividade")
    private String ultimaAtividade;

    @Column(name = "data_ultima_atividade")
    private LocalDateTime dataUltimaAtividade;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "carga_trabalho")
    private Integer cargaTrabalho;

    @Column(name = "eficiencia")
    private Double eficiencia;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "equipe_id", nullable = false)
    private Equipe equipe;

    @ElementCollection
    @CollectionTable(name = "habilidades_membro")
    @Column(name = "habilidade")
    private List<String> habilidades;

    @ElementCollection
    @CollectionTable(name = "certificacoes_membro")
    @Column(name = "certificacao")
    private List<String> certificacoes;

    @Column(name = "observacoes", columnDefinition = "TEXT")
    private String observacoes;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public enum Status {
        ATIVO, INATIVO, FERIAS, AFASTADO
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (status == null) {
            status = Status.ATIVO;
        }
        disponivel = true;
        if (eficiencia == null) {
            eficiencia = 1.0;
        }
        if (cargaTrabalho == null) {
            cargaTrabalho = 0;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
} 