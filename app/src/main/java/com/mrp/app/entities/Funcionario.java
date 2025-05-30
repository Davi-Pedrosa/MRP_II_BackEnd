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
@Table(name = "funcionarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nomeFuncionario;
    private Double salarioHora;
    private Double horaTrabalho;
    private Double horasTrabalhadas;
    private String cargo;
    private String departamento;
    
    public void registrarHorasTrabalhadas(Double horas) {
        this.horasTrabalhadas = (this.horasTrabalhadas == null ? 0 : this.horasTrabalhadas) + horas;
    }
    
    public Double calcularCustoFuncionario() {
        return this.salarioHora * (this.horasTrabalhadas == null ? 0 : this.horasTrabalhadas);
    }
} 