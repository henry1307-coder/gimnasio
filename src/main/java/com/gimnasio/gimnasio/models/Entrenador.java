package com.gimnasio.gimnasio.models;

import jakarta.persistence.*;

@Entity
@Table(name = "entrenadores")
public class Entrenador {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id")
    private Long usuarioId;
    private String especialidad;
    private String telefono;

    public Long getId() { return id; }
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long id) { this.usuarioId = id; }
    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String e) { this.especialidad = e; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String t) { this.telefono = t; }
}