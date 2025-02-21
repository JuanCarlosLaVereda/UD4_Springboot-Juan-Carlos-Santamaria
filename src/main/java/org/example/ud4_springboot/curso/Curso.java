package org.example.ud4_springboot.curso;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.ud4_springboot.estudiante.Estudiante;
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
@Table(name="cursos", uniqueConstraints = {@UniqueConstraint(columnNames = {"nombre"})})
public class Curso {

    @Id
    @GeneratedValue
    Integer id;

    @Basic
    @Column
    String nombre;

    String descripcion;

    @ManyToMany(mappedBy = "cursos")
    Set<Estudiante> estudiantes = new HashSet<>();


}
