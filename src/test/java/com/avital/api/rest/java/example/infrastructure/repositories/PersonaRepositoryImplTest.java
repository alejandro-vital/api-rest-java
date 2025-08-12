package com.avital.api.rest.java.example.infrastructure.repositories;

import com.avital.api.rest.java.example.domain.entities.Persona;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PersonaRepositoryImplTest {

    private PersonaRepositoryImpl personaRepository;

    @BeforeEach
    void setUp() {
        personaRepository = new PersonaRepositoryImpl();
    }

    @Test
    void findByNumeroTelefonico_ConNumeroExistente_DeberiaRetornarPersona() {
        // Given
        String numeroTelefonico = "5551234567";

        // When
        Optional<Persona> resultado = personaRepository.findByNumeroTelefonico(numeroTelefonico);

        // Then
        assertTrue(resultado.isPresent());

        Persona persona = resultado.get();
        assertEquals(numeroTelefonico, persona.getNumeroTelefonico());
        assertEquals("Juan", persona.getNombre());
        assertEquals("García", persona.getApellidoPaterno());
        assertEquals("López", persona.getApellidoMaterno());
    }

    @Test
    void findByNumeroTelefonico_ConNumeroNoExistente_DeberiaRetornarEmpty() {
        // Given
        String numeroInexistente = "5559999999";

        // When
        Optional<Persona> resultado = personaRepository.findByNumeroTelefonico(numeroInexistente);

        // Then
        assertTrue(resultado.isEmpty());
    }

    @Test
    void findByNumeroTelefonico_VerificarTodasLasPersonasExistentes() {
        // Test Juan García López
        Optional<Persona> juan = personaRepository.findByNumeroTelefonico("5551234567");
        assertTrue(juan.isPresent());
        assertEquals("Juan", juan.get().getNombre());
        assertEquals("García", juan.get().getApellidoPaterno());
        assertEquals("López", juan.get().getApellidoMaterno());

        // Test María Rodríguez Martínez
        Optional<Persona> maria = personaRepository.findByNumeroTelefonico("5552345678");
        assertTrue(maria.isPresent());
        assertEquals("María", maria.get().getNombre());
        assertEquals("Rodríguez", maria.get().getApellidoPaterno());
        assertEquals("Martínez", maria.get().getApellidoMaterno());

        // Test Carlos Fernández González
        Optional<Persona> carlos = personaRepository.findByNumeroTelefonico("5553456789");
        assertTrue(carlos.isPresent());
        assertEquals("Carlos", carlos.get().getNombre());
        assertEquals("Fernández", carlos.get().getApellidoPaterno());
        assertEquals("González", carlos.get().getApellidoMaterno());

        // Test Ana Morales Jiménez
        Optional<Persona> ana = personaRepository.findByNumeroTelefonico("5554567890");
        assertTrue(ana.isPresent());
        assertEquals("Ana", ana.get().getNombre());
        assertEquals("Morales", ana.get().getApellidoPaterno());
        assertEquals("Jiménez", ana.get().getApellidoMaterno());
    }

    @Test
    void findByNumeroTelefonico_ConNumeroNull_DeberiaRetornarEmpty() {
        // When
        Optional<Persona> resultado = personaRepository.findByNumeroTelefonico(null);

        // Then
        assertTrue(resultado.isEmpty());
    }

    @Test
    void findByNumeroTelefonico_ConStringVacio_DeberiaRetornarEmpty() {
        // When
        Optional<Persona> resultado = personaRepository.findByNumeroTelefonico("");

        // Then
        assertTrue(resultado.isEmpty());
    }

    @Test
    void verificarInicializacionCorrecta() {
        // Verificar que se inicializaron exactamente 4 personas
        String[] numerosEsperados = {"5551234567", "5552345678", "5553456789", "5554567890"};

        for (String numero : numerosEsperados) {
            Optional<Persona> persona = personaRepository.findByNumeroTelefonico(numero);
            assertTrue(persona.isPresent(),
                    "Debería existir una persona con número: " + numero);
        }
    }
}