package com.gimnasio.gimnasio.dto;

import java.time.LocalDateTime;
import java.util.List;

public class RutinaDetalleDTO {
    private Long id;
    private String nombre;
    private String nivel;
    private String descripcion;
    private Long entrenadorId;
    private String entrenadorNombre;
    private LocalDateTime createdAt;
    private List<EjercicioDTO> ejercicios;

    // Constructor vacío
    public RutinaDetalleDTO() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String n) { this.nombre = n; }
    public String getNivel() { return nivel; }
    public void setNivel(String n) { this.nivel = n; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String d) { this.descripcion = d; }
    public Long getEntrenadorId() { return entrenadorId; }
    public void setEntrenadorId(Long id) { this.entrenadorId = id; }
    public String getEntrenadorNombre() { return entrenadorNombre; }
    public void setEntrenadorNombre(String n) { this.entrenadorNombre = n; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime c) { this.createdAt = c; }
    public List<EjercicioDTO> getEjercicios() { return ejercicios; }
    public void setEjercicios(List<EjercicioDTO> e) { this.ejercicios = e; }
}