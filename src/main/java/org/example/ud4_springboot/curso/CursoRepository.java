package org.example.ud4_springboot.curso;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Integer>{
    Optional<Curso> findByNombre(String nombre);
}
