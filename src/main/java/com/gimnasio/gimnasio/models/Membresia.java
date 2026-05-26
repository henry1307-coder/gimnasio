package com.gimnasio.gimnasio.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "membresias")
public class Membresia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Usuario cliente;

    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    @Enumerated(EnumType.STRING)
    private Estado estado = Estado.Activa;

    public enum Estado { Activa, Vencida, Cancelada }

    
    public Long getId() { return id; }
    public Usuario getCliente() { return cliente; }
    public void setCliente(Usuario c) { this.cliente = c; }
    public Plan getPlan() { return plan; }
    public void setPlan(Plan p) { this.plan = p; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate f) { this.fechaInicio = f; }
    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate f) { this.fechaFin = f; }
    public Estado getEstado() { return estado; }
    public void setEstado(Estado e) { this.estado = e; }
}