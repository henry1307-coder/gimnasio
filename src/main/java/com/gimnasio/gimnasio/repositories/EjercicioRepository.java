package com.gimnasio.gimnasio.repositories;

import com.gimnasio.gimnasio.models.Ejercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EjercicioRepository extends JpaRepository<Ejercicio, Long> {
    List<Ejercicio> findByRutinaId(Long rutinaId);
    void deleteByRutinaId(Long rutinaId);
}