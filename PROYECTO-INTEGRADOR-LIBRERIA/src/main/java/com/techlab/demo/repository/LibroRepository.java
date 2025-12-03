package com.techlab.demo.repository;

import com.techlab.demo.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    // Spring Data JPA implementa automáticamente estos métodos derivados
    List<Libro> findByTitulo(String titulo);

    List<Libro> findByAutor(String autor);

    List<Libro> findByCategoria(String categoria);

    List<Libro> findByTituloAndAutor(String titulo, String autor);
}