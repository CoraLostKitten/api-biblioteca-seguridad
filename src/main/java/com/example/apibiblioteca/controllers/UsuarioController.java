package com.example.apibiblioteca.controllers;

import com.example.apibiblioteca.entities.Usuario;
import com.example.apibiblioteca.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/perfil")
    public ResponseEntity<?> obtenerPerfil(Authentication authentication) {
        String emailConectado = authentication.getName();

        // Buscamos al usuario
        java.util.Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(emailConectado);

        // Comprobamos si existe manualmente para evitar errores de tipos con el .map
        if (usuarioOpt.isPresent()) {
            return ResponseEntity.ok(usuarioOpt.get());
        } else {
            return ResponseEntity.status(404).body("Usuario no encontrado");
        }
    }
}