package com.gimnasio.gimnasio.repositories;

import com.gimnasio.gimnasio.models.Rutina;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface RutinaRepository extends JpaRepository<Rutina, Long> {
    List<Rutina> findByEntrenadorId(Long entrenadorId);
    List<Rutina> findByNivel(Rutina.Nivel nivel);
}