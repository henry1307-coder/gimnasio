package com.gimnasio.gimnasio.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "rutinas")
public class Rutina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Enumerated(EnumType.STRING)
    private Nivel nivel;
    
    @JsonProperty("entrenadorId")
    @Column(name = "entrenador_id")
    private Long entrenadorId;

    private String descripcion;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    // Relación con ejercicios — se eliminan junto con la rutina
    @OneToMany(mappedBy = "rutina", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ejercicio> ejercicios;

    public enum Nivel { Principiante, Intermedio, Avanzado }

    
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String n) { this.nombre = n; }
    public Nivel getNivel() { return nivel; }
    public void setNivel(Nivel n) { this.nivel = n; }
    public Long getEntrenadorId() { return entrenadorId; }
    public void setEntrenadorId(Long id) { this.entrenadorId = id; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String d) { this.descripcion = d; }
    public List<Ejercicio> getEjercicios() { return ejercicios; }
    public void setEjercicios(List<Ejercicio> e) { this.ejercicios = e; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}