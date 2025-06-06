package com.mrp2.backend.dto;

import com.mrp2.backend.model.OrdemProducao;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OrdemProducaoDTO {
    private Long id;
    private String numeroPedido;
    private String produto;
    private Integer quantidade;
    private OrdemProducao.Status status;
    private LocalDateTime dataPrevisao;
    private String descricao;
    private OrdemProducao.Prioridade prioridade;
    private String observacoes;
    private Double custoEstimado;
    private Integer tempoEstimado;
    private Long usuarioId;
    private String usuarioNome;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static OrdemProducaoDTO fromEntity(OrdemProducao ordemProducao) {
        OrdemProducaoDTO dto = new OrdemProducaoDTO();
        dto.setId(ordemProducao.getId());
        dto.setNumeroPedido(ordemProducao.getNumeroPedido());
        dto.setProduto(ordemProducao.getProduto());
        dto.setQuantidade(ordemProducao.getQuantidade());
        dto.setStatus(ordemProducao.getStatus());
        dto.setDataPrevisao(ordemProducao.getDataPrevisao());
        dto.setDescricao(ordemProducao.getDescricao());
        dto.setPrioridade(ordemProducao.getPrioridade());
        dto.setObservacoes(ordemProducao.getObservacoes());
        dto.setCustoEstimado(ordemProducao.getCustoEstimado());
        dto.setTempoEstimado(ordemProducao.getTempoEstimado());
        dto.setUsuarioId(ordemProducao.getUsuario().getId());
        dto.setUsuarioNome(ordemProducao.getUsuario().getNome());
        dto.setCreatedAt(ordemProducao.getCreatedAt());
        dto.setUpdatedAt(ordemProducao.getUpdatedAt());
        return dto;
    }
} 