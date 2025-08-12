package com.avital.api.rest.java.example.application.exceptions;

public class PersonaNotFoundException extends DomainException {
    public static final String ERROR_CODE = "PERSONA_NOT_FOUND";

    public PersonaNotFoundException(String numeroTelefonico) {
        super("No se encontró una persona con el número telefónico: " + numeroTelefonico, ERROR_CODE);
    }

}