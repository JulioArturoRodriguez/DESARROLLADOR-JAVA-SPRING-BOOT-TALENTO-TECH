
package com.techlab.demo.service;

import com.techlab.demo.model.Libro;
import java.util.List;
import java.util.Optional;

public interface LibroService {

    Libro crearLibro(Libro libro);

    void borrarLibro(Libro libro);

    Optional<Libro> buscarLibroPorId(Long id);

    // Método único para listar con filtros
    List<Libro> listarLibros(String titulo, String autor, String categoria);

    // nuevo método para editar
    Libro editarLibro(Long id, Libro libro);
}

