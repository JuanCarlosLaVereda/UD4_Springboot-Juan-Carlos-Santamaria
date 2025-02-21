package org.example.ud4_springboot.curso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public Curso addCurso(Curso curso) {
        return cursoRepository.save(curso);
    }
}
