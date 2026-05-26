package com.gimnasio.gimnasio.controllers;

import com.gimnasio.gimnasio.models.Cliente;
import com.gimnasio.gimnasio.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired private ClienteRepository clienteRepo;

    @GetMapping
    public List<Cliente> listar() {
        return clienteRepo.findAll();
    }
}


