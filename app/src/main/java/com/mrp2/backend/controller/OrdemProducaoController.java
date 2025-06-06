package com.mrp2.backend.controller;

import com.mrp2.backend.dto.OrdemProducaoDTO;
import com.mrp2.backend.model.OrdemProducao;
import com.mrp2.backend.service.OrdemProducaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.mrp2.backend.model.Usuario;

import java.util.List;

@RestController
@RequestMapping("/ordens-producao")
@RequiredArgsConstructor
public class OrdemProducaoController {
    private final OrdemProducaoService ordemProducaoService;

    @PostMapping
    public ResponseEntity<OrdemProducaoDTO> criar(
            @RequestBody OrdemProducaoDTO dto,
            @AuthenticationPrincipal Usuario usuario) {
        return ResponseEntity.ok(ordemProducaoService.criar(dto, usuario.getId()));
    }

    @GetMapping
    public ResponseEntity<List<OrdemProducaoDTO>> listar(
            @AuthenticationPrincipal Usuario usuario) {
        if (usuario.getPapel() == Usuario.Role.ADMIN) {
            return ResponseEntity.ok(ordemProducaoService.listarTodas());
        } else {
            return ResponseEntity.ok(ordemProducaoService.listarPorUsuario(usuario.getId()));
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<OrdemProducaoDTO> atualizarStatus(
            @PathVariable Long id,
            @RequestParam OrdemProducao.Status status) {
        return ResponseEntity.ok(ordemProducaoService.atualizarStatus(id, status));
    }
} 