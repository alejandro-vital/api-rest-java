package com.avital.api.rest.java.example.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "Respuesta de búsqueda de persona")
public class PersonaResponseDto {
    @Schema(description = "Mensaje de resultado", example = "Persona encontrada exitosamente")
    @JsonProperty("mensaje")
    private String mensaje;

    @Schema(description = "Datos de la persona encontrada")
    @JsonProperty("data")
    private PersonaDataDto data;

    @Schema(description = "Fecha y hora de la respuesta", example = "2025-08-11T14:30:00")
    @JsonProperty("fecha_hora")
    private LocalDateTime timestamp;

    public PersonaResponseDto() {
        this.timestamp = LocalDateTime.now();
    }

    public PersonaResponseDto(String mensaje, PersonaDataDto data) {
        this();
        this.mensaje = mensaje;
        this.data = data;
    }

    public PersonaResponseDto(String mensaje, String nombre, String apellidoPaterno, String apellidoMaterno) {
        this();
        this.mensaje = mensaje;
        this.data = new PersonaDataDto(nombre, apellidoPaterno, apellidoMaterno);
    }
}
