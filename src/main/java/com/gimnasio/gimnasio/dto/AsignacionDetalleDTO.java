package com.gimnasio.gimnasio.dto;

import java.time.LocalDate;

public class AsignacionDetalleDTO {
    private Long id;
    private Long rutinaId;
    private String rutinaNombre;
    private String rutinaNivel;
    private Long clienteId;
    private String clienteNombre;
    private String clienteApellido;
    private String entrenadorNombre;
    private LocalDate fechaAsignacion;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getRutinaId() { return rutinaId; }
    public void setRutinaId(Long id) { this.rutinaId = id; }
    public String getRutinaNombre() { return rutinaNombre; }
    public void setRutinaNombre(String n) { this.rutinaNombre = n; }
    public String getRutinaNivel() { return rutinaNivel; }
    public void setRutinaNivel(String n) { this.rutinaNivel = n; }
    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long id) { this.clienteId = id; }
    public String getClienteNombre() { return clienteNombre; }
    public void setClienteNombre(String n) { this.clienteNombre = n; }
    public String getClienteApellido() { return clienteApellido; }
    public void setClienteApellido(String a) { this.clienteApellido = a; }
    public String getEntrenadorNombre() { return entrenadorNombre; }
    public void setEntrenadorNombre(String n) { this.entrenadorNombre = n; }
    public LocalDate getFechaAsignacion() { return fechaAsignacion; }
    public void setFechaAsignacion(LocalDate f) { this.fechaAsignacion = f; }
}