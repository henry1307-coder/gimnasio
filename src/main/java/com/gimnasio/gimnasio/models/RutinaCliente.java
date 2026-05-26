package com.gimnasio.gimnasio.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "rutinas_clientes")
public class RutinaCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rutina_id")
    private Long rutinaId;

    @Column(name = "cliente_id")
    private Long clienteId;

    private LocalDate fechaAsignacion;

   
    public Long getId() { return id; }
    public Long getRutinaId() { return rutinaId; }
    public void setRutinaId(Long id) { this.rutinaId = id; }
    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long id) { this.clienteId = id; }
    public LocalDate getFechaAsignacion() { return fechaAsignacion; }
    public void setFechaAsignacion(LocalDate f) { this.fechaAsignacion = f; }
}