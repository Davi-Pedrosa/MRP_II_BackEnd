package com.mrp2.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "solicitacoes_manutencao")
public class SolicitacaoManutencao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String equipamento;
    private String descricao;
    
    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;
    
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "solicitado_por_id")
    private Usuario solicitadoPor;

    @ManyToOne
    @JoinColumn(name = "atribuido_para_id")
    private Usuario atribuidoPara;

    private String departamento;
    
    @Column(name = "data_solicitacao")
    private LocalDateTime dataSolicitacao;
    
    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;
    
    private String observacoes;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public enum Prioridade {
        ALTA, MEDIA, BAIXA
    }

    public enum Status {
        PENDENTE, EM_ANDAMENTO, CONCLUIDA
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        dataSolicitacao = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
} 