package com.gimnasio.gimnasio.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthResponse {
    private Long id;   
    @JsonProperty("clienteId")       
    private Long clienteId; 
    @JsonProperty("entrenadorId")  
    private Long entrenadorId;
    private String nombre;
    private String apellido;
    private String email;
    private String rol;

    public AuthResponse(Long id, String nombre, String apellido,
                        String email, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.rol = rol;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public Long getEntrenadorId() { return entrenadorId; }
    public void setEntrenadorId(Long entrenadorId) { this.entrenadorId = entrenadorId; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getEmail() { return email; }
    public String getRol() { return rol; }
}