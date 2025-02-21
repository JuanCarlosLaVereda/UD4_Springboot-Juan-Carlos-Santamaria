package org.example.ud4_springboot.estudianteCurso;

import org.example.ud4_springboot.curso.Curso;
import org.example.ud4_springboot.curso.CursoRepository;
import org.example.ud4_springboot.estudiante.Estudiante;
import org.example.ud4_springboot.estudiante.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EstudianteCursoService {

    @Autowired
    EstudianteRepository estudianteRepository;

    @Autowired
    CursoRepository cursoRepository;

    public Map<Estudiante, Curso> addCursoEstudiante(Integer estudianteId, Integer cursoId) {

        Estudiante estudiante = estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        estudiante.getCursos().add(curso);
        curso.getEstudiantes().add(estudiante);

        estudianteRepository.save(estudiante);

        return Map.of(estudiante, curso);
    }
}
