package com.pet.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMascota;
    @ManyToOne
    private Dueno dueno;
    private String tipoMascota;
    private int edad;
    private String nombreMascota;
}
