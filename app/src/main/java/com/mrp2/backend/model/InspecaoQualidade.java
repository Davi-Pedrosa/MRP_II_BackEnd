package com.mrp2.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "inspecoes_qualidade")
public class InspecaoQualidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "produto_id")
    private String produtoId;

    @Column(name = "numero_lote")
    private String numeroLote;

    @ManyToOne
    @JoinColumn(name = "inspecionado_por_id")
    private Usuario inspecionadoPor;

    @Column(name = "data_inspecao")
    private LocalDateTime dataInspecao;

    @ElementCollection
    @CollectionTable(name = "parametros_inspecao")
    private List<ParametroInspecao> parametros;

    private String observacoes;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ElementCollection
    @CollectionTable(name = "anexos_inspecao")
    private List<Anexo> anexos;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Embeddable
    @Data
    public static class ParametroInspecao {
        private String nome;
        private Double valor;
        private String unidade;
        
        @Enumerated(EnumType.STRING)
        private StatusParametro status;

        public enum StatusParametro {
            APROVADO, REPROVADO
        }
    }

    @Embeddable
    @Data
    public static class Anexo {
        private String id;
        private String url;
        private String tipo;
    }

    public enum Status {
        APROVADO, REPROVADO
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        dataInspecao = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
} 