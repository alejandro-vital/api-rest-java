package com.avital.api.rest.java.example.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Schema(description = "Datos de entrada para buscar una persona por número telefónico")
public record TelefonoRequestDto(
        @Schema(
                description = "Número telefónico de 10 dígitos",
                example = "5551234567",
                pattern = "^[0-9]{10}$"
        )
        @NotBlank(message = "El número telefónico no puede estar vacío")
        @Pattern(regexp = "^[0-9]{10}$", message = "El número telefónico debe tener 10 dígitos y/o contener solo números")
        @JsonProperty("numeroTelefonico")
        String numeroTelefonico
) {}
