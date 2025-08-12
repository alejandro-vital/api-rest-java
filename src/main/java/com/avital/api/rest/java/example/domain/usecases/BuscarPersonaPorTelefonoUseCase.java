package com.avital.api.rest.java.example.domain.usecases;

import com.avital.api.rest.java.example.application.exceptions.PersonaNotFoundException;
import com.avital.api.rest.java.example.domain.entities.Persona;
import com.avital.api.rest.java.example.domain.repositories.IPersonaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class BuscarPersonaPorTelefonoUseCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(BuscarPersonaPorTelefonoUseCase.class);
    private final IPersonaRepository personaRepository;

    public BuscarPersonaPorTelefonoUseCase(IPersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public Persona execute(String numeroTelefonico) {
        LOGGER.info("===Iniciando búsqueda para número telefónico: {}===", numeroTelefonico);

        return personaRepository.findByNumeroTelefonico(numeroTelefonico)
                .orElseThrow(() -> {
                    LOGGER.warn("===Persona no encontrada para número: {}===", numeroTelefonico);
                    return new PersonaNotFoundException(numeroTelefonico);
                });
    }
}