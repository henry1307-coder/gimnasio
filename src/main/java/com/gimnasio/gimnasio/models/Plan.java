package com.gimnasio.gimnasio.models;

import jakarta.persistence.*;

@Entity
@Table(name = "planes")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false)
    private Integer duracionDias;

    @Column(nullable = false)
    private Double precio;

    private String descripcion;

    
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String n) { this.nombre = n; }
    public Integer getDuracionDias() { return duracionDias; }
    public void setDuracionDias(Integer d) { this.duracionDias = d; }
    public Double getPrecio() { return precio; }
    public void setPrecio(Double p) { this.precio = p; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String d) { this.descripcion = d; }
}