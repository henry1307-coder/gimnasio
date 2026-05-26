package com.gimnasio.gimnasio.dto;

import java.time.LocalDate;

public class MembresiaRequestDTO {
    private Long clienteId;
    private Long planId;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estado;

    // Constructores, Getters y Setters
    public MembresiaRequestDTO() {}

    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }

    public Long getPlanId() { return planId; }
    public void setPlanId(Long planId) { this.planId = planId; }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}