package org.example.ud4_springboot.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    String nombre;
    String email;
    String edad;
    String usuario;
    String contrasenya;
}