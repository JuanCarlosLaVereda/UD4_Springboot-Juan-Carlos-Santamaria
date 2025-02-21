package org.example.ud4_springboot.estudianteCurso;

import org.example.ud4_springboot.curso.Curso;
import org.example.ud4_springboot.estudiante.Estudiante;
import org.example.ud4_springboot.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class EstudianteCursoController {


    @Autowired
    private EstudianteCursoService estudianteCursoService;

    @PostMapping("cursar/{idUsuario}/{idCurso}")
    private ResponseEntity<?> addCursoAEstudiante(@AuthenticationPrincipal Estudiante estudiante, @PathVariable("idUsuario") Integer idUsuario, @PathVariable("idCurso") Integer idCurso) {
        try {
            return ResponseEntity.ok(new Result.Success<Map<Estudiante, Curso>>(estudianteCursoService.addCursoEstudiante(idUsuario, idCurso)));
        } catch (Exception e) {
            return ResponseEntity.ok(new Result.Error<Map<Estudiante, Curso>>(e.getMessage()));
        }
    }

}
