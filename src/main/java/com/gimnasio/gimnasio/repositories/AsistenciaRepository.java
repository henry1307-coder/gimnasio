package com.gimnasio.gimnasio.repositories;

import com.gimnasio.gimnasio.models.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {

    List<Asistencia> findByClienteId(Long clienteId);

    // Buscar entrada abierta (sin salida) de un cliente
    Optional<Asistencia> findByClienteIdAndFechaSalidaIsNull(Long clienteId);

    // Asistencias de hoy
    @Query("SELECT a FROM Asistencia a WHERE a.fechaEntrada >= :inicio AND a.fechaEntrada <= :fin")
    List<Asistencia> findByFechaEntradaBetween(LocalDateTime inicio, LocalDateTime fin);
}