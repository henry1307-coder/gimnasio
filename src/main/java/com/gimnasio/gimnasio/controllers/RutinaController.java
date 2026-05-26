package com.gimnasio.gimnasio.controllers;

import com.gimnasio.gimnasio.dto.AsignacionDetalleDTO;
import com.gimnasio.gimnasio.dto.EjercicioDTO;
import com.gimnasio.gimnasio.dto.RutinaDetalleDTO;
import com.gimnasio.gimnasio.models.Ejercicio;
import com.gimnasio.gimnasio.models.Rutina;
import com.gimnasio.gimnasio.models.RutinaCliente;

import com.gimnasio.gimnasio.repositories.EntrenadorRepository;
import com.gimnasio.gimnasio.repositories.ClienteRepository;
import com.gimnasio.gimnasio.repositories.RutinaClienteRepository;
import com.gimnasio.gimnasio.repositories.RutinaRepository;
import com.gimnasio.gimnasio.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rutinas")
@CrossOrigin(origins = "*")
public class RutinaController {

    @Autowired private RutinaRepository rutinaRepo;
    @Autowired private RutinaClienteRepository asigRepo;
    @Autowired private UsuarioRepository usuarioRepo;
    @Autowired private EntrenadorRepository entrenadorRepo;
    @Autowired private ClienteRepository clienteRepo;

    // ── CONVERSORES ─────────────────────────────────────────────

    private RutinaDetalleDTO toDTO(Rutina r) {
        RutinaDetalleDTO dto = new RutinaDetalleDTO();
        dto.setId(r.getId());
        dto.setNombre(r.getNombre());
        dto.setNivel(r.getNivel() != null ? r.getNivel().name() : null);
        dto.setDescripcion(r.getDescripcion());
        dto.setEntrenadorId(r.getEntrenadorId());
        dto.setCreatedAt(r.getCreatedAt());

        // Buscar nombre del entrenador
        if (r.getEntrenadorId() != null) {
            entrenadorRepo.findById(r.getEntrenadorId()).ifPresent(ent ->
                usuarioRepo.findById(ent.getUsuarioId()).ifPresent(u ->
                    dto.setEntrenadorNombre(u.getNombre() + " " + u.getApellido())
                )
            );
        }

        // Convertir ejercicios
        if (r.getEjercicios() != null) {
            List<EjercicioDTO> ejDTOs = r.getEjercicios().stream().map(e -> {
                EjercicioDTO ejDTO = new EjercicioDTO();
                ejDTO.setId(e.getId());
                ejDTO.setNombre(e.getNombre());
                ejDTO.setSeries(e.getSeries());
                ejDTO.setRepeticiones(e.getRepeticiones());
                ejDTO.setDescansoSeg(e.getDescansoSeg());
                ejDTO.setNotas(e.getNotas());
                return ejDTO;
            }).collect(Collectors.toList());
            dto.setEjercicios(ejDTOs);
        }

        return dto;
    }

    private AsignacionDetalleDTO toAsigDTO(RutinaCliente rc) {
        AsignacionDetalleDTO dto = new AsignacionDetalleDTO();
        dto.setId(rc.getId());
        dto.setRutinaId(rc.getRutinaId());
        dto.setClienteId(rc.getClienteId());
        dto.setFechaAsignacion(rc.getFechaAsignacion());

        // Nombre de la rutina y entrenador
        rutinaRepo.findById(rc.getRutinaId()).ifPresent(rutina -> {
            dto.setRutinaNombre(rutina.getNombre());
            dto.setRutinaNivel(rutina.getNivel() != null ? rutina.getNivel().name() : null);
            if (rutina.getEntrenadorId() != null) {
                entrenadorRepo.findById(rutina.getEntrenadorId()).ifPresent(ent ->
                    usuarioRepo.findById(ent.getUsuarioId()).ifPresent(u ->
                        dto.setEntrenadorNombre(u.getNombre() + " " + u.getApellido())
                    )
                );
            }
        });

        // Nombre del cliente
        clienteRepo.findById(rc.getClienteId()).ifPresent(cliente ->
            usuarioRepo.findById(cliente.getUsuarioId()).ifPresent(u -> {
                dto.setClienteNombre(u.getNombre());
                dto.setClienteApellido(u.getApellido());
            })
        );

        return dto;
    }

    // ── ENDPOINTS RUTINAS ────────────────────────────────────────

    @GetMapping
    public List<RutinaDetalleDTO> listar() {
        return rutinaRepo.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RutinaDetalleDTO> getById(@PathVariable Long id) {
        return rutinaRepo.findById(id)
                .map(r -> ResponseEntity.ok(toDTO(r)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Rutina rutina) {
        try {
            if (rutina.getEjercicios() != null) {
                for (Ejercicio e : rutina.getEjercicios()) {
                    e.setRutina(rutina);
                }
            }
            Rutina guardada = rutinaRepo.save(rutina);
            return ResponseEntity.ok(toDTO(guardada));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al guardar rutina: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable Long id, @RequestBody Rutina datos) {
        return rutinaRepo.findById(id).map(r -> {
            r.setNombre(datos.getNombre());
            r.setNivel(datos.getNivel());
            r.setDescripcion(datos.getDescripcion());
            r.setEntrenadorId(datos.getEntrenadorId());
            r.getEjercicios().clear();
            if (datos.getEjercicios() != null) {
                for (Ejercicio e : datos.getEjercicios()) {
                    e.setRutina(r);
                    r.getEjercicios().add(e);
                }
            }
            return ResponseEntity.ok(toDTO(rutinaRepo.save(r)));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (!rutinaRepo.existsById(id))
            return ResponseEntity.notFound().build();
        rutinaRepo.deleteById(id);
        return ResponseEntity.ok("Rutina eliminada");
    }

    // ── ENDPOINTS ASIGNACIONES ───────────────────────────────────

    @GetMapping("/asignaciones")
    public List<AsignacionDetalleDTO> listarAsignaciones() {
        return asigRepo.findAll().stream()
                .map(this::toAsigDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/asignaciones")
    public ResponseEntity<?> asignar(@RequestBody RutinaCliente asig) {
        try {
            if (asig.getFechaAsignacion() == null)
                asig.setFechaAsignacion(LocalDate.now());
            RutinaCliente guardada = asigRepo.save(asig);
            return ResponseEntity.ok(toAsigDTO(guardada));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al asignar rutina: " + e.getMessage());
        }
    }

    @DeleteMapping("/asignaciones/{id}")
    public ResponseEntity<?> quitarAsignacion(@PathVariable Long id) {
        if (!asigRepo.existsById(id))
            return ResponseEntity.notFound().build();
        asigRepo.deleteById(id);
        return ResponseEntity.ok("Asignación eliminada");
    }
}