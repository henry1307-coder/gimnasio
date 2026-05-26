package com.gimnasio.gimnasio.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellido;

    @Column(unique = true, nullable = false, length = 150)
    private String email;

    @Column(nullable = false)
    private String password; // guardado con BCrypt

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol; // ADMIN, ENTRENADOR, CLIENTE

    private boolean activo = true;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public enum Rol { ADMIN, ENTRENADOR, CLIENTE }

    
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String n) { this.nombre = n; }
    public String getApellido() { return apellido; }
    public void setApellido(String a) { this.apellido = a; }
    public String getEmail() { return email; }
    public void setEmail(String e) { this.email = e; }
    public String getPassword() { return password; }
    public void setPassword(String p) { this.password = p; }
    public Rol getRol() { return rol; }
    public void setRol(Rol r) { this.rol = r; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean a) { this.activo = a; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}