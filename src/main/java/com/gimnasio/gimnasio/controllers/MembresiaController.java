package com.gimnasio.gimnasio.controllers;

import com.gimnasio.gimnasio.models.Membresia;
import com.gimnasio.gimnasio.models.Plan;
import com.gimnasio.gimnasio.models.Usuario; // O el nombre exacto de tu modelo de Cliente/Usuario
import com.gimnasio.gimnasio.repositories.MembresiaRepository;
import com.gimnasio.gimnasio.repositories.PlanRepository;
import com.gimnasio.gimnasio.repositories.UsuarioRepository; // Asegúrate de importar tu repositorio de usuarios/clientes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class MembresiaController {

    @Autowired private MembresiaRepository membresiaRepo;
    @Autowired private PlanRepository planRepo;
    @Autowired private UsuarioRepository usuarioRepo; // Necesario para buscar al cliente real

    // ── PLANES ──────────────────────────────────
    @GetMapping("/api/planes")
    public List<Plan> listarPlanes() { return planRepo.findAll(); }

    @PostMapping("/api/planes")
    public Plan crearPlan(@RequestBody Plan plan) { return planRepo.save(plan); }

    @PutMapping("/api/planes/{id}")
    public ResponseEntity<Plan> editarPlan(@PathVariable Long id, @RequestBody Plan datos) {
        return planRepo.findById(id).map(p -> {
            p.setNombre(datos.getNombre());
            p.setDuracionDias(datos.getDuracionDias());
            p.setPrecio(datos.getPrecio());
            p.setDescripcion(datos.getDescripcion());
            return ResponseEntity.ok(planRepo.save(p));
        }).orElse(ResponseEntity.notFound().build());
    }

    // ── MEMBRESÍAS ───────────────────────────────
    @GetMapping("/api/membresias")
    public List<Membresia> listar() { return membresiaRepo.findAll(); }

    @PostMapping("/api/membresias")
    public ResponseEntity<?> crear(@RequestBody MembresiaDTO dto) {
        try {
            // Buscar las entidades reales en la base de datos SQL
            Usuario cliente = usuarioRepo.findById(dto.getClienteId())
                    .orElseThrow(() -> new RuntimeException("El cliente con ID " + dto.getClienteId() + " no existe en la BD SQL."));
            
            Plan plan = planRepo.findById(dto.getPlanId())
                    .orElseThrow(() -> new RuntimeException("El plan con ID " + dto.getPlanId() + " no existe en la BD SQL."));

            Membresia m = new Membresia();
            m.setCliente(cliente);
            m.setPlan(plan);
            m.setFechaInicio(dto.getFechaInicio() != null ? dto.getFechaInicio() : LocalDate.now());
            m.setFechaFin(dto.getFechaFin());
            
            // Manejo seguro del Enum con formato "Activa"
            if (dto.getEstado() == null) {
                m.setEstado(Membresia.Estado.Activa);
            } else {
                String estadoJS = dto.getEstado().trim();
                if (estadoJS.equalsIgnoreCase("Activa")) m.setEstado(Membresia.Estado.Activa);
                else if (estadoJS.equalsIgnoreCase("Cancelada")) m.setEstado(Membresia.Estado.Cancelada);
                else if (estadoJS.equalsIgnoreCase("Vencida")) m.setEstado(Membresia.Estado.Vencida);
                else m.setEstado(Membresia.Estado.Activa);
            }

            return ResponseEntity.ok(membresiaRepo.save(m));
            
        } catch (Exception e) {
            e.printStackTrace();
            // Te responderá al frontend exactamente cuál ID hizo falta
            return ResponseEntity.status(500).body("Error en Java: " + e.getMessage());
        }
    }

    @PutMapping("/api/membresias/{id}")
    public ResponseEntity<?> editar(@PathVariable Long id, @RequestBody MembresiaDTO dto) {
        return membresiaRepo.findById(id).map(m -> {
            try {
                Usuario cliente = usuarioRepo.findById(dto.getClienteId())
                        .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
                Plan plan = planRepo.findById(dto.getPlanId())
                        .orElseThrow(() -> new RuntimeException("Plan no encontrado"));

                m.setCliente(cliente);
                m.setPlan(plan);
                m.setFechaInicio(dto.getFechaInicio());
                m.setFechaFin(dto.getFechaFin());
                if (dto.getEstado() != null) m.setEstado(Membresia.Estado.valueOf(dto.getEstado()));

                return ResponseEntity.ok(membresiaRepo.save(m));
            } catch (Exception e) {
                return ResponseEntity.status(500).body("Error al editar: " + e.getMessage());
            }
        }).orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/api/membresias/{id}/cancelar")
    public ResponseEntity<Membresia> cancelar(@PathVariable Long id) {
        return membresiaRepo.findById(id).map(m -> {
            m.setEstado(Membresia.Estado.Cancelada);
            return ResponseEntity.ok(membresiaRepo.save(m));
        }).orElse(ResponseEntity.notFound().build());
    }

    // ── CLASE INTERNA (DTO) PARA MAPEAR EL JSON DEL FRONTEND ────────────────
    // Esta clase estructural recibe los datos planos de JavaScript perfectamente.
    public static class MembresiaDTO {
        private Long clienteId;
        private Long planId;
        private LocalDate fechaInicio;
        private LocalDate fechaFin;
        private String estado;

        // Getters y Setters
        public Long getClienteId() { return clienteId; }
        public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
        public Long getPlanId() { return planId; }
        public void setPlanId(Long planId) { this.planId = planId; }
        public LocalDate getFechaInicio() { return fechaInicio; }
        public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }
        public LocalDate getFechaFin() { return fechaFin; }
        public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }
        public String getEstado() { return estado; }
        public void setEstado(String estado) { this.estado = estado; }
    }
}