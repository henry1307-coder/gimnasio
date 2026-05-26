package com.gimnasio.gimnasio.controllers;

import com.gimnasio.gimnasio.models.Asistencia;
import com.gimnasio.gimnasio.repositories.AsistenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class AsistenciaController {

    @Autowired private AsistenciaRepository asistenciaRepo;

    // ── LISTAR ASISTENCIAS (Coincide con tu JS: GET /api/asistencia) ──
    @GetMapping("/api/asistencia")
    public List<Asistencia> listar() { 
        return asistenciaRepo.findAll(); 
    }

    // ── REGISTRAR ENTRADA (Coincide con tu JS: POST /api/asistencia) ──
    @PostMapping("/api/asistencia")
    public ResponseEntity<?> crear(@RequestBody Asistencia asistencia) {
        try {
            // Si el frontend no envía la fecha, usamos la hora del servidor
            if (asistencia.getFechaEntrada() == null) {
                asistencia.setFechaEntrada(LocalDateTime.now());
            }
            asistencia.setFechaSalida(null); // Entra, por lo tanto no ha salido

            return ResponseEntity.ok(asistenciaRepo.save(asistencia));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error en Java: " + e.getMessage());
        }
    }

    // ── REGISTRAR SALIDA (Coincide con tu JS: PATCH /api/asistencia/{id}/salida) ──
    @PatchMapping("/api/asistencia/{id}/salida")
    public ResponseEntity<?> registrarSalida(@PathVariable Long id) {
        return asistenciaRepo.findById(id).map(a -> {
            a.setFechaSalida(LocalDateTime.now());
            return ResponseEntity.ok(asistenciaRepo.save(a));
        }).orElse(ResponseEntity.notFound().build());
    }

    // ── ELIMINAR (Coincide con tu JS: DELETE /api/asistencia/{id}) ──
    @DeleteMapping("/api/asistencia/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (asistenciaRepo.existsById(id)) {
            asistenciaRepo.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
