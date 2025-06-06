package com.mrp2.backend.service;

import com.mrp2.backend.dto.OrdemProducaoDTO;
import com.mrp2.backend.model.OrdemProducao;
import com.mrp2.backend.model.Usuario;
import com.mrp2.backend.repository.OrdemProducaoRepository;
import com.mrp2.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdemProducaoService {
    private final OrdemProducaoRepository ordemProducaoRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public OrdemProducaoDTO criar(OrdemProducaoDTO dto, Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        OrdemProducao ordemProducao = new OrdemProducao();
        ordemProducao.setNumeroPedido(dto.getNumeroPedido());
        ordemProducao.setProduto(dto.getProduto());
        ordemProducao.setQuantidade(dto.getQuantidade());
        ordemProducao.setStatus(OrdemProducao.Status.PENDENTE);
        ordemProducao.setDataPrevisao(dto.getDataPrevisao());
        ordemProducao.setUsuario(usuario);

        ordemProducao = ordemProducaoRepository.save(ordemProducao);
        return OrdemProducaoDTO.fromEntity(ordemProducao);
    }

    @Transactional(readOnly = true)
    public List<OrdemProducaoDTO> listarTodas() {
        return ordemProducaoRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(OrdemProducaoDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<OrdemProducaoDTO> listarPorUsuario(Long usuarioId) {
        return ordemProducaoRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(OrdemProducaoDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public OrdemProducaoDTO atualizarStatus(Long id, OrdemProducao.Status novoStatus) {
        OrdemProducao ordemProducao = ordemProducaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ordem de produção não encontrada"));

        ordemProducao.setStatus(novoStatus);
        ordemProducao = ordemProducaoRepository.save(ordemProducao);
        return OrdemProducaoDTO.fromEntity(ordemProducao);
    }
} 