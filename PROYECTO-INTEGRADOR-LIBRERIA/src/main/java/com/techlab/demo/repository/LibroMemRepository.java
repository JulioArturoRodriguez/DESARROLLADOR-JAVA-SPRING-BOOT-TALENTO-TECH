package com.techlab.demo.repository;

import com.techlab.demo.model.Libro;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@Profile("local")   // este repositorio solo se activa con el perfil local
public class LibroMemRepository {

    private static Long nextId = 1L;
    private final ArrayList<Libro> libros;

    public LibroMemRepository() {
        this.libros = obtenerLibrosIniciales();
    }

    public Libro save(Libro libro) {
        if (libro.getId() == null) {
            updateId(libro);
            libros.add(libro);
        } else {
            Optional<Libro> existente = findById(libro.getId());
            if (existente.isPresent()) {
                Libro libroExistente = existente.get();
                libroExistente.setTitulo(libro.getTitulo());
                libroExistente.setAutor(libro.getAutor());
                libroExistente.setCategoria(libro.getCategoria());
                libroExistente.setAnioPublicacion(libro.getAnioPublicacion());
            } else {
                libros.add(libro);
            }
        }
        return libro;
    }

    public void delete(Libro libro) {
        libros.remove(libro);
    }

    public Optional<Libro> findById(Long id) {
        return libros.stream()
                .filter(libro -> Objects.equals(libro.getId(), id))
                .findFirst();
    }

    public List<Libro> findAll() {
        return new ArrayList<>(libros);
    }

    public List<Libro> findByTitulo(String titulo) {
        return filtrarPorCampo("titulo", titulo);
    }

    public List<Libro> findByAutor(String autor) {
        return filtrarPorCampo("autor", autor);
    }

    public List<Libro> findByCategoria(String categoria) {
        return filtrarPorCampo("categoria", categoria);
    }

    public List<Libro> findByTituloAndAutor(String titulo, String autor) {
        ArrayList<Libro> encontrados = new ArrayList<>();
        for (Libro libro : libros) {
            if (estaIncluido(libro.getTitulo(), titulo) &&
                    estaIncluido(libro.getAutor(), autor)) {
                encontrados.add(libro);
            }
        }
        return encontrados;
    }

    // --- Lógica interna ---
    private List<Libro> filtrarPorCampo(String campo, String valor) {
        ArrayList<Libro> encontrados = new ArrayList<>();
        for (Libro libro : libros) {
            String texto = switch (campo) {
                case "titulo" -> libro.getTitulo();
                case "autor" -> libro.getAutor();
                case "categoria" -> libro.getCategoria();
                default -> "";
            };
            if (estaIncluido(texto, valor)) {
                encontrados.add(libro);
            }
        }
        return encontrados;
    }

    private boolean estaIncluido(String textoCompleto, String textoParcial) {
        return formatoBusqueda(textoCompleto).contains(formatoBusqueda(textoParcial));
    }

    private String formatoBusqueda(String texto) {
        return texto == null ? "" : texto.trim().toLowerCase();
    }

    private ArrayList<Libro> obtenerLibrosIniciales() {
        ArrayList<Libro> iniciales = new ArrayList<>();
        iniciales.add(new Libro("Cien años de soledad", "Gabriel García Márquez", "Novela", 1967));
        iniciales.add(new Libro("El Principito", "Antoine de Saint-Exupéry", "Infantil", 1943));
        iniciales.add(new Libro("Don Quijote de la Mancha", "Miguel de Cervantes", "Clásico", 1605));
        iniciales.add(new Libro("1984", "George Orwell", "Distopía", 1949));
        iniciales.add(new Libro("La sombra del viento", "Carlos Ruiz Zafón", "Novela", 2001));
        for (Libro libro : iniciales) {
            updateId(libro);
        }
        return iniciales;
    }

    private void updateId(Libro libro) {
        libro.setId(nextId);
        nextId++;
    }
}