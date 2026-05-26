package com.gimnasio.gimnasio.repositories;

import com.gimnasio.gimnasio.models.RutinaCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface RutinaClienteRepository extends JpaRepository<RutinaCliente, Long> {
    List<RutinaCliente> findByClienteId(Long clienteId);
    List<RutinaCliente> findByRutinaId(Long rutinaId);
}