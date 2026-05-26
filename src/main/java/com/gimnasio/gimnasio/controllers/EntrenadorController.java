package com.gimnasio.gimnasio.controllers;

import com.gimnasio.gimnasio.models.Entrenador;
import com.gimnasio.gimnasio.repositories.EntrenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/entrenadores")
@CrossOrigin(origins = "*")
public class EntrenadorController {

    @Autowired private EntrenadorRepository entrenadorRepo;

    @GetMapping
    public List<Entrenador> listar() {
        return entrenadorRepo.findAll();
    }
}
