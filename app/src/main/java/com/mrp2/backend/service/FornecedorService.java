package com.mrp2.backend.service;

import com.mrp2.backend.model.Fornecedor;
import com.mrp2.backend.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FornecedorService {
    @Autowired
    private FornecedorRepository repository;

    public Fornecedor save(Fornecedor fornecedor) {
        return repository.save(fornecedor);
    }

    public List<Fornecedor> findAll() {
        return repository.findAll();
    }

    public Fornecedor findById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado com id: " + id));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Fornecedor update(Long id, Fornecedor fornecedor) {
        Fornecedor existingFornecedor = findById(id);
        
        // Atualizar campos
        existingFornecedor.setNome(fornecedor.getNome());
        existingFornecedor.setEmail(fornecedor.getEmail());
        existingFornecedor.setTelefone(fornecedor.getTelefone());
        existingFornecedor.setEndereco(fornecedor.getEndereco());
        existingFornecedor.setPessoaContato(fornecedor.getPessoaContato());

        return repository.save(existingFornecedor);
    }
} 