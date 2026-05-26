package com.gimnasio.gimnasio.controllers;


import com.gimnasio.gimnasio.dto.AuthRequest;
import com.gimnasio.gimnasio.dto.AuthResponse;
import com.gimnasio.gimnasio.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    // POST /api/auth/registro
    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@RequestBody AuthRequest req) {
        try {
            AuthResponse res = authService.registrar(req);
            return ResponseEntity.ok(res);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // POST /api/auth/login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        try {
            AuthResponse res = authService.login(req);
            return ResponseEntity.ok(res);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}
