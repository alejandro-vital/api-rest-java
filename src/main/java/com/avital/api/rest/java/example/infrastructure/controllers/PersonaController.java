package com.avital.api.rest.java.example.infrastructure.controllers;

import com.avital.api.rest.java.example.application.dto.ErrorResponseDto;
import com.avital.api.rest.java.example.application.dto.PersonaResponseDto;
import com.avital.api.rest.java.example.application.dto.TelefonoRequestDto;
import com.avital.api.rest.java.example.application.exceptions.PersonaNotFoundException;
import com.avital.api.rest.java.example.application.services.PersonaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/personas")
@CrossOrigin(origins = "*")
public class PersonaController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonaController.class);
    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @PostMapping("/buscar")
    @Operation(summary = "Buscar persona por número telefónico")
    public ResponseEntity<PersonaResponseDto > buscarPersonaPorTelefono(
            @Valid @RequestBody TelefonoRequestDto request) {

        LOGGER.info("===Recibida solicitud de búsqueda: {}===", request);
        PersonaResponseDto  response = personaService.buscarPersonaPorTelefono(request);
        return ResponseEntity.ok(response);
    }




    // Manejo global de excepciones
    @ExceptionHandler(PersonaNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handlePersonaNotFound(PersonaNotFoundException ex) {
        LOGGER.warn("===Persona no encontrada: {}===", ex.getMessage());

        ErrorResponseDto error = new ErrorResponseDto(
                "Persona no encontrada",
                ex.getMessage(),
                ex.getErrorCode()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationErrors(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        String message = fieldError != null ? fieldError.getDefaultMessage() : "Error de validación";

        LOGGER.warn("===Error de validación: {}===", message);

        ErrorResponseDto error = new ErrorResponseDto(
                "Datos de entrada inválidos",
                message,
                "VALIDATION_ERROR"
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> handleIllegalArgument(IllegalArgumentException ex) {
        LOGGER.warn("===Argumento inválido: {}===", ex.getMessage());

        ErrorResponseDto error = new ErrorResponseDto(
                "Argumento inválido",
                ex.getMessage(),
                "INVALID_ARGUMENT"
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGenericError(Exception ex) {
        LOGGER.error("===Error interno del servidor===", ex);

        ErrorResponseDto error = new ErrorResponseDto(
                "Error interno del servidor",
                "Ha ocurrido un error inesperado. Por favor, intente más tarde.",
                "INTERNAL_ERROR"
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}