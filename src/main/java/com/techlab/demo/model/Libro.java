package com.techlab.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String autor;
    private String categoria;
    private Integer anioPublicacion;

    // Constructor específico para el repositorio en memoria
    public Libro(String titulo, String autor, String categoria, Integer anioPublicacion) {
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.anioPublicacion = anioPublicacion;
    }
}