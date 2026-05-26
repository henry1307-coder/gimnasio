package com.gimnasio.gimnasio.repositories;

import com.gimnasio.gimnasio.models.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EntrenadorRepository extends JpaRepository<Entrenador, Long> {
    Optional<Entrenador> findByUsuarioId(Long usuarioId);
}