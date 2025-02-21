package org.example.ud4_springboot.estudiante;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {
    Optional<Estudiante> findByUsername(String username);
}
