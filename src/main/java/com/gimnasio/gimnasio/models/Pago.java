package com.gimnasio.gimnasio.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pagos")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cliente_id")
    private Long clienteId;

    private Double monto;
    private String metodo;     // Efectivo, Tarjeta, Transferencia
    private String concepto;   // Membresía mensual, anual, etc.
    private LocalDate fechaPago;

    
    public Long getId() { return id; }
    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long id) { this.clienteId = id; }
    public Double getMonto() { return monto; }
    public void setMonto(Double m) { this.monto = m; }
    public String getMetodo() { return metodo; }
    public void setMetodo(String m) { this.metodo = m; }
    public String getConcepto() { return concepto; }
    public void setConcepto(String c) { this.concepto = c; }
    public LocalDate getFechaPago() { return fechaPago; }
    public void setFechaPago(LocalDate f) { this.fechaPago = f; }
}