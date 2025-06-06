package com.mrp2.backend.controller;

import com.mrp2.backend.model.Usuario;
import com.mrp2.backend.service.AuthService;
import com.mrp2.backend.service.dto.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        try {
            String token = authService.login(credentials.get("email"), credentials.get("senha"));
            return ResponseEntity.ok(Map.of(
                "token", token,
                "message", "Login realizado com sucesso"
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                "error", "Erro ao fazer login",
                "message", e.getMessage()
            ));
        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario) {
        try {
            Usuario novoUsuario = authService.registrar(usuario);
            return ResponseEntity.ok(AuthResponse.fromUsuario(novoUsuario));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                "error", "Erro ao registrar usuário",
                "message", e.getMessage()
            ));
        }
    }
} 