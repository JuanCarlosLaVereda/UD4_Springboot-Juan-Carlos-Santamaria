package org.example.ud4_springboot.auth;
import lombok.RequiredArgsConstructor;

import org.example.ud4_springboot.estudiante.Estudiante;
import org.example.ud4_springboot.estudiante.EstudianteRepository;
import org.example.ud4_springboot.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private EstudianteRepository estudianteRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        UserDetails user= estudianteRepository.findByUsername(request.getUsername()).orElseThrow();

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();

    }


    public AuthResponse register(RegisterRequest request) {
        Estudiante estudiante = Estudiante.builder()
                .name(request.getNombre())
                .email(request.getEmail())
                .edad(request.getEdad())
                .username(request.getUsuario())
                .password(passwordEncoder.encode(request.getContrasenya()))
                .build();

        estudianteRepository.save(estudiante);

        return AuthResponse.builder()
                .token(jwtService.getToken(estudiante))
                .build();
    }
}
