package com.example.entrega2.controller;

import com.example.entrega2.entity.Cliente;
import com.example.entrega2.service.ClienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> getAll() {
        return clienteService.findAll();
    }


    @GetMapping("/{cedula}")
    public Cliente getById(@PathVariable String cedula) {
        return clienteService.findById(cedula).orElse(null);
    }


    @PostMapping
    public Cliente create(@RequestBody Cliente cliente) {
        return clienteService.save(cliente);
    }


    @PutMapping("/{cedula}")
    public Cliente update(@PathVariable String cedula, @RequestBody Cliente cliente) {
        cliente.setCedula(cedula);
        return clienteService.save(cliente);
    }


    @DeleteMapping("/{cedula}")
    public void delete(@PathVariable String cedula) {
        clienteService.delete(cedula);
    }
}
