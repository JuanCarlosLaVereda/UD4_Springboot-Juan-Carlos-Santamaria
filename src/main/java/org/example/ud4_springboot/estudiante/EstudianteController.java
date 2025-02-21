package org.example.ud4_springboot.estudiante;


import org.example.ud4_springboot.curso.Curso;
import org.example.ud4_springboot.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    Logger logger = Logger.getLogger(EstudianteController.class.getName());

    @GetMapping
    private ResponseEntity<?> getEstudiantes(@AuthenticationPrincipal Estudiante estudiante) {
        logger.info("Recibida peticion de mostrar todos los estudiantes");
        return ResponseEntity.ok(estudianteService.findAll());
    }

    @PutMapping("update")
    private ResponseEntity<?> updateEstudiante(@RequestBody Estudiante estudianteBody, @AuthenticationPrincipal Estudiante estudiante) {
        logger.info("Recibida peticion de actualizar estudiante");
        return ResponseEntity.ok(estudianteService.updateEstudiante(estudianteBody));
    }

    @DeleteMapping("delete/{id}")
    private ResponseEntity<?> deleteEstudiante(@PathVariable("id") Integer id, @AuthenticationPrincipal Estudiante estudiante) {
        try {
            logger.info("Recibida peticion de borrar estudiante");
            return ResponseEntity.ok(new Result.Success<Estudiante>(estudianteService.deleteEstudiante(id)));
        } catch (Exception e) {
            logger.info("Excepcion en peticion de borrar estudiante");
            return ResponseEntity.ok(new Result.Error<Estudiante>(e.getMessage()));
        }
    }

}
