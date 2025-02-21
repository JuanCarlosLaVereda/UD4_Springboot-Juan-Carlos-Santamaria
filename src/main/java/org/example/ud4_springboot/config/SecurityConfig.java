package org.example.ud4_springboot.config;

import lombok.RequiredArgsConstructor;
import org.example.ud4_springboot.jwt.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{


        return http
                .csrf(AbstractHttpConfigurer::disable) // Deshabilitar la protecccion csrf (Cross-Site Request Forgery)
                .authorizeHttpRequests( authRequest -> authRequest
                        .requestMatchers("/auth/**").permitAll() // Allow routes that starts with /auth/  (public routes)
                        .anyRequest().authenticated()) // Any other route requires authenticate
                //.formLogin(Customizer.withDefaults())
                .sessionManagement(sessionManager -> sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)

                .build();

    }

}

