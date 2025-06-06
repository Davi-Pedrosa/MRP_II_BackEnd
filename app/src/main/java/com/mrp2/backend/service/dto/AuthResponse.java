package com.mrp2.backend.service.dto;

import com.mrp2.backend.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private Long id;
    private String nome;
    private String email;
    private Usuario.Role papel;
    private String token;

    public static AuthResponse fromUsuario(Usuario usuario) {
        return AuthResponse.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .papel(usuario.getPapel())
                .build();
    }

    public static AuthResponse fromUsuarioWithToken(Usuario usuario, String token) {
        return AuthResponse.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .papel(usuario.getPapel())
                .token(token)
                .build();
    }
} 