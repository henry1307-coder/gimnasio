package com.gimnasio.gimnasio.controllers;

import com.gimnasio.gimnasio.models.Pago;
import com.gimnasio.gimnasio.repositories.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/pagos")
@CrossOrigin(origins = "*")
public class PagoController {

    @Autowired
    private PagoRepository repo;

    @GetMapping
    public List<Pago> listar() { return repo.findAll(); }

    @GetMapping("/cliente/{clienteId}")
    public List<Pago> porCliente(@PathVariable Long clienteId) {
        return repo.findByClienteId(clienteId);
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Pago pago) {
    try {
        if (pago.getFechaPago() == null) {
            pago.setFechaPago(LocalDate.now());
        }
        return ResponseEntity.ok(repo.save(pago));
    } catch (Exception e) {
        return ResponseEntity.status(500).body("Error al guardar pago: " + e.getMessage());
    }
}

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (!repo.existsById(id))
            return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.ok("Pago eliminado");
    }
}