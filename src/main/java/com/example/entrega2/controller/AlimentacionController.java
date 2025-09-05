package com.example.entrega2.controller;

import com.example.entrega2.entity.Alimentacion;
import com.example.entrega2.service.AlimentacionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alimentaciones")
@CrossOrigin(origins = "*")
public class AlimentacionController {

    private final AlimentacionService alimentacionService;

    public AlimentacionController(AlimentacionService alimentacionService) {
        this.alimentacionService = alimentacionService;
    }

    @GetMapping
    public List<Alimentacion> getAll() {
        return alimentacionService.findAll();
    }

    @GetMapping("/{id}")
    public Alimentacion getById(@PathVariable Long id) {
        return alimentacionService.findById(id).orElse(null);
    }

    @PostMapping
    public Alimentacion create(@RequestBody Alimentacion alimentacion) {
        return alimentacionService.save(alimentacion);
    }

    @PutMapping("/{id}")
    public Alimentacion update(@PathVariable Long id, @RequestBody Alimentacion alimentacion) {
        alimentacion.setId(id);
        return alimentacionService.save(alimentacion);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        alimentacionService.delete(id);
    }
}
