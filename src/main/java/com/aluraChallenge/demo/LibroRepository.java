package com.aluraChallenge.demo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libros, Long> {
    Optional<Libros> findByTitle(String titulo);

    boolean existsByTitle(String title);
}
