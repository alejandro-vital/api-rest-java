package com.avital.api.rest.java.example.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

@Schema(description = "Información de una persona")
public record PersonaDataDto(
        @Schema(description = "Nombre de la persona", example = "Juan")
        @JsonProperty("nombre")
        String nombre,

        @Schema(description = "Apellido paterno", example = "García")
        @JsonProperty("apellidoPaterno")
        String apellidoPaterno,

        @Schema(description = "Apellido materno", example = "López")
        @JsonProperty("apellidoMaterno")
        String apellidoMaterno
) {
    public PersonaDataDto {
        Objects.requireNonNull(nombre, "El nombre no puede ser null");
    }
}
