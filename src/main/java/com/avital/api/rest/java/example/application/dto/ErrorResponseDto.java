package com.avital.api.rest.java.example.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponseDto {

    @JsonProperty("error")
    private String error;

    @JsonProperty("mensaje")
    private String mensaje;

    @JsonProperty("codigo")
    private String codigo;

    @JsonProperty("fecha_hora")
    private LocalDateTime timestamp;

    public ErrorResponseDto() {
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponseDto(String error, String mensaje, String codigo) {
        this();
        this.error = error;
        this.mensaje = mensaje;
        this.codigo = codigo;
    }

}
