package com.gimnasio.gimnasio.repositories;

import com.gimnasio.gimnasio.models.Membresia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MembresiaRepository extends JpaRepository<Membresia, Long> {
    List<Membresia> findByClienteId(Long clienteId);
    List<Membresia> findByEstado(String estado);
}