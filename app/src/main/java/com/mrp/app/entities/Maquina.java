package com.mrp.app.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "maquinas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Maquina {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    private Double durabilidadeFerramenta; // em porcentagem (0-100)
    private Double custoManutencao;
    private String modelo;
    private String fabricante;
    private Double horasUso;
    
    public void atualizarDurabilidade(Double horasUtilizadas) {
        // Assume-se que a cada hora de uso, a durabilidade diminui 0.5%
        Double reducaoDurabilidade = horasUtilizadas * 0.5;
        this.durabilidadeFerramenta = Math.max(0, this.durabilidadeFerramenta - reducaoDurabilidade);
        this.horasUso = (this.horasUso == null ? 0 : this.horasUso) + horasUtilizadas;
    }
    
    public Double calcularCustoManutencao() {
        // O custo de manutenção aumenta conforme a durabilidade diminui
        Double fatorDesgaste = (100 - this.durabilidadeFerramenta) / 100;
        return this.custoManutencao * (1 + fatorDesgaste);
    }
} 