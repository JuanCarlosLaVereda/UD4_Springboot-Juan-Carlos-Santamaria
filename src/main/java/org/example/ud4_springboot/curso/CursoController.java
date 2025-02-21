package org.example.ud4_springboot.curso;


import org.example.ud4_springboot.estudiante.Estudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    public ResponseEntity<?> addCurso(@RequestBody Curso curso, @AuthenticationPrincipal Estudiante estudiante) {
        return ResponseEntity.ok(cursoService.addCurso(curso));
    }
}
