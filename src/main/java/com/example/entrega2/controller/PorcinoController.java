package com.example.entrega2.controller;

import com.example.entrega2.entity.Porcino;
import com.example.entrega2.service.PorcinoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/porcinos")
@CrossOrigin(origins = "*")
public class PorcinoController {

    private final PorcinoService porcinoService;

    public PorcinoController(PorcinoService porcinoService) {
        this.porcinoService = porcinoService;
    }

    @GetMapping
    public List<Porcino> getAll() {
        return porcinoService.findAll();
    }

    @GetMapping("/{id}")
    public Porcino getById(@PathVariable Long id) {
        return porcinoService.findById(id).orElse(null);
    }

    @PostMapping
    public Porcino create(@RequestBody Porcino porcino) {
        return porcinoService.save(porcino);
    }

    @PutMapping("/{id}")
    public Porcino update(@PathVariable Long id, @RequestBody Porcino porcino) {
        porcino.setId(id);
        return porcinoService.save(porcino);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        porcinoService.delete(id);
    }
}
