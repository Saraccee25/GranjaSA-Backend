package com.example.entrega2.entity;

import jakarta.persistence.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Porcino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identificacion;

    @Enumerated(EnumType.STRING)
    private Raza raza;

    private int edad;
    private double peso;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "alimentacion_id", referencedColumnName = "id")
    private Alimentacion alimentacion;
}
