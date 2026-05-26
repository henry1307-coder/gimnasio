package com.gimnasio.gimnasio.services;

import com.gimnasio.gimnasio.dto.AuthRequest;
import com.gimnasio.gimnasio.dto.AuthResponse;
import com.gimnasio.gimnasio.models.Cliente;
import com.gimnasio.gimnasio.models.Entrenador;
import com.gimnasio.gimnasio.models.Usuario;
import com.gimnasio.gimnasio.repositories.ClienteRepository;
import com.gimnasio.gimnasio.repositories.EntrenadorRepository;
import com.gimnasio.gimnasio.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired private UsuarioRepository repo;
    @Autowired private ClienteRepository clienteRepo;
    @Autowired private EntrenadorRepository entrenadorRepo;
    @Autowired private PasswordEncoder passwordEncoder;

    public AuthResponse registrar(AuthRequest req) {
        if (repo.existsByEmail(req.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }

        // Guardar usuario
        Usuario u = new Usuario();
        u.setNombre(req.getNombre());
        u.setApellido(req.getApellido());
        u.setEmail(req.getEmail());
        u.setPassword(passwordEncoder.encode(req.getPassword()));
        u.setRol(Usuario.Rol.valueOf(req.getRol() != null ? req.getRol() : "CLIENTE"));
        u.setActivo(true);
        repo.save(u);

        // Crear registro en tabla clientes o entrenadores
        if (u.getRol() == Usuario.Rol.CLIENTE) {
            Cliente c = new Cliente();
            c.setUsuarioId(u.getId());
            clienteRepo.save(c);
        } else if (u.getRol() == Usuario.Rol.ENTRENADOR) {
            Entrenador e = new Entrenador();
            e.setUsuarioId(u.getId());
            entrenadorRepo.save(e);
        }

        return new AuthResponse(u.getId(), u.getNombre(),
                u.getApellido(), u.getEmail(), u.getRol().name());
    }

    public AuthResponse login(AuthRequest req) {
    Usuario u = repo.findByEmail(req.getEmail())
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    if (!u.isActivo())
        throw new RuntimeException("Cuenta desactivada");
    if (!passwordEncoder.matches(req.getPassword(), u.getPassword()))
        throw new RuntimeException("Contraseña incorrecta");

    AuthResponse res = new AuthResponse(u.getId(), u.getNombre(),
            u.getApellido(), u.getEmail(), u.getRol().name());

    // Agregar el id de clientes o entrenadores
    if (u.getRol() == Usuario.Rol.CLIENTE) {
        clienteRepo.findByUsuarioId(u.getId())
            .ifPresent(c -> res.setClienteId(c.getId()));
    } else if (u.getRol() == Usuario.Rol.ENTRENADOR) {
        entrenadorRepo.findByUsuarioId(u.getId())
            .ifPresent(e -> res.setEntrenadorId(e.getId()));
    }

    return res;
}
}