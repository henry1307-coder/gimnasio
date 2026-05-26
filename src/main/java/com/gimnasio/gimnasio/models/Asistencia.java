package com.gimnasio.gimnasio.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "asistencia")
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cliente_id")
    private Long clienteId;

    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida; // null = sigue dentro

   
    public Long getId() { return id; }
    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long id) { this.clienteId = id; }
    public LocalDateTime getFechaEntrada() { return fechaEntrada; }
    public void setFechaEntrada(LocalDateTime f) { this.fechaEntrada = f; }
    public LocalDateTime getFechaSalida() { return fechaSalida; }
    public void setFechaSalida(LocalDateTime f) { this.fechaSalida = f; }
}