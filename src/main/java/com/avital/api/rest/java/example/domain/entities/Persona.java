package com.avital.api.rest.java.example.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persona {
    private String numeroTelefonico;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;

    public String getNombreCompleto() {
        return Stream.of(nombre, apellidoPaterno, apellidoMaterno)
                .filter(Objects::nonNull)
                .filter(s -> !s.trim().isEmpty())
                .collect(Collectors.joining(" "));
    }
}
