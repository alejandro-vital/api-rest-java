package com.avital.api.rest.java.example.domain.usecases;

import com.avital.api.rest.java.example.application.exceptions.PersonaNotFoundException;
import com.avital.api.rest.java.example.domain.entities.Persona;
import com.avital.api.rest.java.example.domain.repositories.IPersonaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuscarPersonaPorTelefonoUseCaseTest {

    @Mock
    private IPersonaRepository personaRepository;

    @InjectMocks
    private BuscarPersonaPorTelefonoUseCase buscarPersonaPorTelefonoUseCase;

    private Persona personaEsperada;
    private String numeroTelefonico;

    @BeforeEach
    void setUp() {
        numeroTelefonico = "5551234567";
        personaEsperada = new Persona(numeroTelefonico, "Juan", "García", "López");
    }

    @Test
    void execute_ConNumeroExistente_DeberiaRetornarPersona() {
        // Given
        when(personaRepository.findByNumeroTelefonico(numeroTelefonico))
                .thenReturn(Optional.of(personaEsperada));

        // Cuando
        Persona resultado = buscarPersonaPorTelefonoUseCase.execute(numeroTelefonico);

        // Después
        assertNotNull(resultado);
        assertEquals(numeroTelefonico, resultado.getNumeroTelefonico());
        assertEquals("Juan", resultado.getNombre());
        assertEquals("García", resultado.getApellidoPaterno());
        assertEquals("López", resultado.getApellidoMaterno());

        verify(personaRepository).findByNumeroTelefonico(numeroTelefonico);
    }

    @Test
    void execute_ConNumeroNoExistente_DeberiaLanzarPersonaNotFoundException() {
        // Given
        String numeroInexistente = "5559999999";
        when(personaRepository.findByNumeroTelefonico(numeroInexistente))
                .thenReturn(Optional.empty());

        // When & Then
        PersonaNotFoundException excepcion = assertThrows(
                PersonaNotFoundException.class,
                () -> buscarPersonaPorTelefonoUseCase.execute(numeroInexistente)
        );

        assertEquals("No se encontró una persona con el número telefónico: " + numeroInexistente,
                excepcion.getMessage());
        assertEquals("PERSONA_NOT_FOUND", excepcion.getErrorCode());

        verify(personaRepository).findByNumeroTelefonico(numeroInexistente);
    }

    @Test
    void execute_ConPersonaConApellidoMaternoNulo_DeberiaFuncionar() {
        // Given
        Persona personaSinApellidoMaterno = new Persona(numeroTelefonico, "María", "González", null);
        when(personaRepository.findByNumeroTelefonico(numeroTelefonico))
                .thenReturn(Optional.of(personaSinApellidoMaterno));

        // When
        Persona resultado = buscarPersonaPorTelefonoUseCase.execute(numeroTelefonico);

        // Then
        assertNotNull(resultado);
        assertEquals("María", resultado.getNombre());
        assertEquals("González", resultado.getApellidoPaterno());
        assertNull(resultado.getApellidoMaterno());
    }

    @Test
    void execute_VerificarLlamadaAlRepositorio() {
        // Given
        when(personaRepository.findByNumeroTelefonico(numeroTelefonico))
                .thenReturn(Optional.of(personaEsperada));

        // When
        buscarPersonaPorTelefonoUseCase.execute(numeroTelefonico);

        // Then
        verify(personaRepository, times(1)).findByNumeroTelefonico(numeroTelefonico);
        verifyNoMoreInteractions(personaRepository);
    }
}