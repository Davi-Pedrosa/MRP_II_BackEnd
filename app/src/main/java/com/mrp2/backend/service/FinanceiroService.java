package com.mrp2.backend.service;

import com.mrp2.backend.model.Financeiro;
import com.mrp2.backend.repository.FinanceiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FinanceiroService {
    @Autowired
    private FinanceiroRepository repository;

    public Financeiro save(Financeiro financeiro) {
        return repository.save(financeiro);
    }

    public List<Financeiro> findAll() {
        return repository.findAll();
    }

    public Financeiro findById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Dados financeiros não encontrados com id: " + id));
    }

    public List<Financeiro> findByPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return repository.findByDataBetween(inicio, fim);
    }

    public List<Financeiro> findByDepartamento(String departamento) {
        return repository.findByDepartamento(departamento);
    }

    public List<Financeiro> findByLinhaProduto(String linhaProduto) {
        return repository.findByLinhaProduto(linhaProduto);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Financeiro update(Long id, Financeiro financeiro) {
        Financeiro existingFinanceiro = findById(id);
        
        // Atualizar campos
        existingFinanceiro.setCustoMaoObra(financeiro.getCustoMaoObra());
        existingFinanceiro.setCustoMateriais(financeiro.getCustoMateriais());
        existingFinanceiro.setCustoEquipamentos(financeiro.getCustoEquipamentos());
        existingFinanceiro.setCustoUtilidades(financeiro.getCustoUtilidades());
        existingFinanceiro.setCustoManutencao(financeiro.getCustoManutencao());
        existingFinanceiro.setCustoTotal(financeiro.getCustoTotal());
        existingFinanceiro.setVendasTotais(financeiro.getVendasTotais());
        existingFinanceiro.setPrecoMedioUnidade(financeiro.getPrecoMedioUnidade());
        existingFinanceiro.setUnidadesProduzidas(financeiro.getUnidadesProduzidas());
        existingFinanceiro.setLucroBruto(financeiro.getLucroBruto());
        existingFinanceiro.setLucroLiquido(financeiro.getLucroLiquido());
        existingFinanceiro.setMargemLucro(financeiro.getMargemLucro());
        existingFinanceiro.setVariacaoLucroBruto(financeiro.getVariacaoLucroBruto());
        existingFinanceiro.setVariacaoLucroLiquido(financeiro.getVariacaoLucroLiquido());
        existingFinanceiro.setVariacaoReceita(financeiro.getVariacaoReceita());
        existingFinanceiro.setDepartamento(financeiro.getDepartamento());
        existingFinanceiro.setLinhaProduto(financeiro.getLinhaProduto());

        return repository.save(existingFinanceiro);
    }
} 