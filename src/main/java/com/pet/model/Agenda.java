package com.pet.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Mascota mascota;
    @ManyToOne
    private Dueno dueno;
    private String hora;
    private String fecha;
}

