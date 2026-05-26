package com.gimnasio.gimnasio.dto;

public class EjercicioDTO {
    private Long id;
    private String nombre;
    private Integer series;
    private Integer repeticiones;
    private Integer descansoSeg;
    private String notas;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
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