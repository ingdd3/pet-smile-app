package com.pet.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Dueno {
    @Id
    @Column(length = 20)
    private Long rut;
    private String nombre;
    private String apellido;
    private String direccion;
    private String correo;
    private String telefono;
    private String nombreMascota;
}

