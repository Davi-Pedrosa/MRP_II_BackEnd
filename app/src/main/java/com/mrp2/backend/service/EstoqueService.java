package com.mrp2.backend.service;

import com.mrp2.backend.model.Estoque;
import com.mrp2.backend.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EstoqueService {
    @Autowired
    private EstoqueRepository repository;

    public Estoque save(Estoque estoque) {
        return repository.save(estoque);
    }

    public List<Estoque> findAll() {
        return repository.findAll();
    }

    public Estoque findById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Item de estoque não encontrado com id: " + id));
    }

    public List<Estoque> findByCategoria(String categoria) {
        return repository.findByCategoria(categoria);
    }

    public List<Estoque> findByStatus(Estoque.Status status) {
        return repository.findByStatus(status);
    }

    public List<Estoque> findByQuantidadeMenorOuIgual(Integer quantidade) {
        return repository.findByQuantidadeLessThanEqual(quantidade);
    }

    public List<Estoque> findByFornecedorId(Long fornecedorId) {
        return repository.findByFornecedorId(fornecedorId);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Estoque update(Long id, Estoque estoque) {
        Estoque existingEstoque = findById(id);
        
        // Atualizar campos
        existingEstoque.setNome(estoque.getNome());
        existingEstoque.setCategoria(estoque.getCategoria());
        existingEstoque.setQuantidade(estoque.getQuantidade());
        existingEstoque.setQuantidadeMinima(estoque.getQuantidadeMinima());
        existingEstoque.setQuantidadeMaxima(estoque.getQuantidadeMaxima());
        existingEstoque.setUnidade(estoque.getUnidade());
        existingEstoque.setLocalizacao(estoque.getLocalizacao());
        existingEstoque.setFornecedor(estoque.getFornecedor());

        return repository.save(existingEstoque);
    }

    public Estoque updateQuantity(Long id, Integer quantidade) {
        Estoque estoque = findById(id);
        estoque.setQuantidade(quantidade);
        return repository.save(estoque);
    }

    public List<Estoque> findLowStockItems() {
        return repository.findByStatus(Estoque.Status.BAIXO_ESTOQUE);
    }

    public List<Estoque> findOutOfStockItems() {
        return repository.findByStatus(Estoque.Status.FORA_ESTOQUE);
    }
} 