package com.techlab.demo.controller;

import com.techlab.demo.model.Libro;
import com.techlab.demo.service.LibroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/libros")
public class LibroController {

    private final LibroService service;

    public LibroController(LibroService service) {
        this.service = service;
    }

    @PostMapping
    public Libro crearLibro(@RequestBody Libro libro) {
        return service.crearLibro(libro);
    }

    @DeleteMapping("/{id}")
    public void borrarLibro(@PathVariable Long id) {
        Optional<Libro> libro = service.buscarLibroPorId(id);
        libro.ifPresent(service::borrarLibro);
    }

    @GetMapping("/{id}")
    public Optional<Libro> buscarLibroPorId(@PathVariable Long id) {
        return service.buscarLibroPorId(id);
    }

    @GetMapping
    public List<Libro> listarLibros(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String autor,
            @RequestParam(required = false) String categoria) {
        return service.listarLibros(titulo, autor, categoria);
    }

    // PUT para editar un libro existente
    @PutMapping("/{id}")
    public Libro editarLibro(@PathVariable Long id, @RequestBody Libro libro) {
        return service.editarLibro(id, libro);
    }


}