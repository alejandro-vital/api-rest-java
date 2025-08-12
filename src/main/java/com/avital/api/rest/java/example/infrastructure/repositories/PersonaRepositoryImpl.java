package com.avital.api.rest.java.example.infrastructure.repositories;

import com.avital.api.rest.java.example.domain.entities.Persona;
import com.avital.api.rest.java.example.domain.repositories.IPersonaRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public class PersonaRepositoryImpl implements IPersonaRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonaRepositoryImpl.class);

    private final Map<String, Persona> personas;

    public PersonaRepositoryImpl() {
        LOGGER.info("===Inicializando datos de prueba...===");

        personas = new HashMap<>();
        // Datos de PRUEBA
        personas.put("5551234567", new Persona("5551234567", "Juan", "García", "López"));
        personas.put("5552345678", new Persona("5552345678", "María", "Rodríguez", "Martínez"));
        personas.put("5553456789", new Persona("5553456789", "Carlos", "Fernández", "González"));
        personas.put("5554567890", new Persona("5554567890", "Ana", "Morales", "Jiménez"));

        LOGGER.info("===Datos de prueba inicializados: {} personas===", personas.size());
    }

    @Override
    public Optional<Persona> findByNumeroTelefonico(String numeroTelefonico) {
        LOGGER.info("===Buscando persona por teléfono: {}===", numeroTelefonico);
        return Optional.ofNullable(personas.get(numeroTelefonico));
    }
}
