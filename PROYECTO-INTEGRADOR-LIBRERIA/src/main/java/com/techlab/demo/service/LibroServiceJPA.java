package com.techlab.demo.service;

import com.techlab.demo.model.Libro;
import com.techlab.demo.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroServiceJPA implements LibroService {

    private final LibroRepository repository;

    public LibroServiceJPA(LibroRepository repository) {
        this.repository = repository;
    }

    @Override
    public Libro crearLibro(Libro libro) {
        return repository.save(libro); // antes: guardarLibro
    }

    @Override
    public void borrarLibro(Libro libro) {
        repository.delete(libro); // antes: borrarLibro
    }

    @Override
    public Optional<Libro> buscarLibroPorId(Long id) {
        return repository.findById(id); // antes: buscarLibroPorId
    }

    @Override
    public List<Libro> listarLibros(String titulo, String autor, String categoria) {
        if (titulo != null && autor != null) {
            return repository.findByTituloAndAutor(titulo, autor); // antes: obtenerLibrosPorTituloYAutor
        } else if (titulo != null) {
            return repository.findByTitulo(titulo); // antes: obtenerLibrosPorTitulo
        } else if (autor != null) {
            return repository.findByAutor(autor); // antes: obtenerLibrosPorAutor
        } else if (categoria != null) {
            return repository.findByCategoria(categoria); // antes: obtenerLibrosPorCategoria
        } else {
            return repository.findAll(); // antes: obtenerLibros
        }
    }

    @Override
    public Libro editarLibro(Long id, Libro libro) {
        Optional<Libro> existente = repository.findById(id); // antes: buscarLibroPorId
        if (existente.isPresent()) {
            Libro libroActualizado = existente.get();

            // ✅ Edición parcial: solo actualiza lo que viene en el JSON
            if (libro.getTitulo() != null) {
                libroActualizado.setTitulo(libro.getTitulo());
            }
            if (libro.getAutor() != null) {
                libroActualizado.setAutor(libro.getAutor());
            }
            if (libro.getCategoria() != null) {
                libroActualizado.setCategoria(libro.getCategoria());
            }
            if (libro.getAnioPublicacion() != 0) { // int no puede ser null
                libroActualizado.setAnioPublicacion(libro.getAnioPublicacion());
            }

            return repository.save(libroActualizado); // antes: guardarLibro
        } else {
            throw new RuntimeException("Libro no encontrado con id " + id);
        }
    }
}