package com.example.entrega2.controller;

import com.example.entrega2.entity.Cliente;
import com.example.entrega2.entity.Porcino;
import com.example.entrega2.service.ClienteService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Controller
public class ClienteGraphQLController {

    private final ClienteService clienteService;

    public ClienteGraphQLController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // --------------------------
    // QUERIES
    // --------------------------

    @QueryMapping
    public List<Cliente> allClientes() {
        return clienteService.findAll();
    }

    @QueryMapping
    public Cliente clienteById(@Argument String cedula) {
        return clienteService.findById(cedula).orElse(null);
    }

    // --------------------------
    // MUTATIONS
    // --------------------------

    @MutationMapping
    public Cliente createCliente(@Argument("input") ClienteInput input) {
        Cliente cliente = new Cliente();
        cliente.setCedula(input.getCedula());
        cliente.setNombres(input.getNombres());
        cliente.setApellidos(input.getApellidos());
        cliente.setDireccion(input.getDireccion());
        cliente.setTelefono(input.getTelefono());
        // Porcinos se podrán asignar mediante otras mutaciones si lo necesitas
        return clienteService.save(cliente);
    }

    @MutationMapping
    public Cliente updateCliente(@Argument String cedula, @Argument("input") ClienteInput input) {
        Optional<Cliente> opt = clienteService.findById(cedula);
        if (opt.isEmpty()) {
            return null; // GraphQL devolverá null si no existe; también puedes lanzar una excepción personalizada
        }
        Cliente existing = opt.get();
        existing.setNombres(input.getNombres());
        existing.setApellidos(input.getApellidos());
        existing.setDireccion(input.getDireccion());
        existing.setTelefono(input.getTelefono());
        return clienteService.save(existing);
    }

    @MutationMapping
    public Boolean deleteCliente(@Argument String cedula) {
        return clienteService.findById(cedula).map(existing -> {
            clienteService.delete(cedula);
            return true;
        }).orElse(false);
    }

    // --------------------------
    // RESOLVERS (nested fields)
    // --------------------------

    /**
     * Resuelve el campo `porcinos` dentro de Cliente.
     * Anotado con @Transactional para permitir la carga perezosa (si corresponde).
     */
    @SchemaMapping(typeName = "Cliente", field = "porcinos")
    @Transactional
    public List<Porcino> porcinos(Cliente cliente) {
        // Si tu Cliente tiene la lista de porcinos mapeada, esto devolverá esa lista.
        // Si usas FetchType.LAZY y tienes problemas, considera cargar explícitamente desde el servicio/repository.
        return cliente.getPorcinos();
    }

    // --------------------------
    // DTO para entradas (GraphQL input)
    // --------------------------
    public static class ClienteInput {
        private String cedula;
        private String nombres;
        private String apellidos;
        private String direccion;
        private String telefono;

        public ClienteInput() {}

        public String getCedula() {
            return cedula;
        }

        public void setCedula(String cedula) {
            this.cedula = cedula;
        }

        public String getNombres() {
            return nombres;
        }

        public void setNombres(String nombres) {
            this.nombres = nombres;
        }

        public String getApellidos() {
            return apellidos;
        }

        public void setApellidos(String apellidos) {
            this.apellidos = apellidos;
        }

        public String getDireccion() {
            return direccion;
        }

        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }
    }
}
