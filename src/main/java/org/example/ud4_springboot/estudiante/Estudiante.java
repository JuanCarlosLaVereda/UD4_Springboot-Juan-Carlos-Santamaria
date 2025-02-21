package org.example.ud4_springboot.estudiante;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.ud4_springboot.curso.Curso;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="estudiantes", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Estudiante implements UserDetails {

    @Id
    @GeneratedValue
    Integer id;

    @Column(nullable = false)
    @Basic
    String username;

    String password;

    String name;

    String email;

    String edad;

    @ManyToMany
    @JoinTable(
            name="estudiante_curso",
            joinColumns = @JoinColumn(name = "estudiante_id"),
            inverseJoinColumns = @JoinColumn(name = "curso_id")
    )
    private Set<Curso> cursos = new HashSet<>();





    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
