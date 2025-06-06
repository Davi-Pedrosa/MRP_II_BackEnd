package com.mrp2.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "financeiros")
public class Financeiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime data;

    // Custos Operacionais
    @Column(name = "custo_mao_obra")
    private BigDecimal custoMaoObra;
    
    @Column(name = "custo_materiais")
    private BigDecimal custoMateriais;
    
    @Column(name = "custo_equipamentos")
    private BigDecimal custoEquipamentos;
    
    @Column(name = "custo_utilidades")
    private BigDecimal custoUtilidades;
    
    @Column(name = "custo_manutencao")
    private BigDecimal custoManutencao;
    
    @Column(name = "custo_total")
    private BigDecimal custoTotal;

    // Receita
    @Column(name = "vendas_totais")
    private BigDecimal vendasTotais;
    
    @Column(name = "preco_medio_unidade")
    private BigDecimal precoMedioUnidade;
    
    @Column(name = "unidades_produzidas")
    private Integer unidadesProduzidas;

    // Lucros
    @Column(name = "lucro_bruto")
    private BigDecimal lucroBruto;
    
    @Column(name = "lucro_liquido")
    private BigDecimal lucroLiquido;
    
    @Column(name = "margem_lucro")
    private BigDecimal margemLucro;

    // Comparação Mensal
    @Column(name = "variacao_lucro_bruto")
    private BigDecimal variacaoLucroBruto;
    
    @Column(name = "variacao_lucro_liquido")
    private BigDecimal variacaoLucroLiquido;
    
    @Column(name = "variacao_receita")
    private BigDecimal variacaoReceita;

    // Metadados
    private String departamento;
    
    @Column(name = "linha_produto")
    private String linhaProduto;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

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