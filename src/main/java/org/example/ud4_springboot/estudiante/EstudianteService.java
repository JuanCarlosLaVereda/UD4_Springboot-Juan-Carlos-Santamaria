package org.example.ud4_springboot.estudiante;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    public List<Estudiante> findAll() {
        return estudianteRepository.findAll();
    }

    public Estudiante updateEstudiante(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    public Estudiante deleteEstudiante(Integer id) {
        Estudiante estudiante = estudianteRepository.findById(id).orElseThrow((Supplier<RuntimeException>) () -> new RuntimeException("Estudiante no encontrado"));
        estudianteRepository.delete(estudiante);
        return estudiante;
    }
}
