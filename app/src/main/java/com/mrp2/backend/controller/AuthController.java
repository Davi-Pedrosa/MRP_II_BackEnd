package com.mrp2.backend.controller;

import com.mrp2.backend.model.Usuario;
import com.mrp2.backend.repository.UsuarioRepository;
import com.mrp2.backend.service.dto.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email já está em uso");
        }

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        Usuario novoUsuario = usuarioRepository.save(usuario);
        
        return ResponseEntity.ok(AuthResponse.fromUsuario(novoUsuario));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        Usuario usuario = usuarioRepository.findByEmail(credentials.get("email"))
            .filter(user -> passwordEncoder.matches(credentials.get("senha"), user.getSenha()))
            .orElse(null);

        if (usuario == null) {
            return ResponseEntity.badRequest().body("Credenciais inválidas");
        }

        return ResponseEntity.ok(AuthResponse.fromUsuario(usuario));
    }
} 