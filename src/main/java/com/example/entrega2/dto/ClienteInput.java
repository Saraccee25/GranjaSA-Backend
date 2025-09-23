package com.example.entrega2.dto;

/**
 * DTO para representar la entrada de datos de un Cliente en GraphQL.
 * Esto evita exponer directamente la entidad JPA en las mutaciones.
 */
public class ClienteInput {
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
