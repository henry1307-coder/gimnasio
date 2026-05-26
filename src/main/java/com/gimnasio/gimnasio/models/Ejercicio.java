package com.gimnasio.gimnasio.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "ejercicios")
public class Ejercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "rutina_id")
    @JsonIgnore // evita bucle infinito al serializar
    private Rutina rutina;

    private String nombre;
    private Integer series;
    private Integer repeticiones;
    private Integer descansoSeg;
    private String notas;

    
    public Long getId() { return id; }
    public Rutina getRutina() { return rutina; }
    public void setRutina(Rutina r) { this.rutina = r; }
    public String getNombre() { return nombre; }
    public void setNombre(String n) { this.nombre = n; }
    public Integer getSeries() { return series; }
    public void setSeries(Integer s) { this.series = s; }
    public Integer getRepeticiones() { return repeticiones; }
    public void setRepeticiones(Integer r) { this.repeticiones = r; }
    public Integer getDescansoSeg() { return descansoSeg; }
    public void setDescansoSeg(Integer d) { this.descansoSeg = d; }
    public String getNotas() { return notas; }
    public void setNotas(String n) { this.notas = n; }
}