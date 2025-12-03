package com.techlab.demo.repository;

import com.techlab.demo.model.Libro;
import java.util.List;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("prod")   // este repositorio solo se activa con el perfil prod
public interface LibroRepositoryJPA extends JpaRepository<Libro, Long>, LibroRepository {

    List<Libro> findByTitulo(String titulo);

    List<Libro> findByTituloContaining(String titulo);

    List<Libro> findByAutorContaining(String autor);

    List<Libro> findByCategoria(String categoria);

    List<Libro> findByTituloContainingAndAutorContaining(String titulo, String autor);
}