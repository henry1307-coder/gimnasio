package com.gimnasio.gimnasio.controllers;

import com.gimnasio.gimnasio.models.Usuario;
import com.gimnasio.gimnasio.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.List;


@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repo;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // GET /api/usuarios
    @GetMapping
    public List<Usuario> listar() {
        return repo.findAll();
    }

    // G
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Long id) {
        return repo.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    //  ej: /api/usuarios/rol/CLIENTE
    @GetMapping("/rol/{rol}")
    public List<Usuario> porRol(@PathVariable String rol) {
        try {
            Usuario.Rol r = Usuario.Rol.valueOf(rol.toUpperCase());
            return repo.findAll().stream()
                .filter(u -> u.getRol() == r)
                .toList();
        } catch (IllegalArgumentException e) {
            return List.of();
        }
    }

    // PUT /api/usuarios/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @RequestBody Usuario datos) {
        return repo.findById(id).map(u -> {
            u.setNombre(datos.getNombre());
            u.setApellido(datos.getApellido());
            u.setRol(datos.getRol());
            u.setActivo(datos.isActivo());
            // Solo cambiar contraseña si se envía una nueva
            if (datos.getPassword() != null && !datos.getPassword().isBlank()) {
                u.setPassword(encoder.encode(datos.getPassword()));
            }
            return ResponseEntity.ok(repo.save(u));
        }).orElse(ResponseEntity.notFound().build());
    }

    // BORRAR
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.ok("Usuario eliminado");
    }

    // activar o desactivar
    @PatchMapping("/{id}/estado")
    public ResponseEntity<?> cambiarEstado(@PathVariable Long id,
                                           @RequestParam boolean activo) {
        return repo.findById(id).map(u -> {
            u.setActivo(activo);
            return ResponseEntity.ok(repo.save(u));
        }).orElse(ResponseEntity.notFound().build());
    }
    // GET /api/usuarios/stats — resumen para el dashboard
    @GetMapping("/stats")
    public ResponseEntity<?> stats() {
    List<Usuario> todos = repo.findAll();
    long total       = todos.size();
    long clientes    = todos.stream().filter(u -> u.getRol() == Usuario.Rol.CLIENTE).count();
    long entrenadores= todos.stream().filter(u -> u.getRol() == Usuario.Rol.ENTRENADOR).count();
    long activos     = todos.stream().filter(u -> u.isActivo()).count();

    return ResponseEntity.ok(Map.of(
        "total",        total,
        "clientes",     clientes,
        "entrenadores", entrenadores,
        "activos",      activos
    ));
}
}