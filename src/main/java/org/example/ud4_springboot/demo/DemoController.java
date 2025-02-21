package org.example.ud4_springboot.demo;

import lombok.RequiredArgsConstructor;
import org.example.ud4_springboot.estudiante.Estudiante;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DemoController {

    @GetMapping(value = "demo")
    public String wellcome(@AuthenticationPrincipal Estudiante estudiante) {
        return "Wellcome "+ estudiante.getName() +" from secure endpoint ";
    }
}
