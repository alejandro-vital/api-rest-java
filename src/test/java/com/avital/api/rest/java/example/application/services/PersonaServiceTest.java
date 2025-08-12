package com.avital.api.rest.java.example.application.services;

import com.avital.api.rest.java.example.application.dto.PersonaResponseDto;
import com.avital.api.rest.java.example.application.dto.TelefonoRequestDto;
import com.avital.api.rest.java.example.application.exceptions.PersonaNotFoundException;
import com.avital.api.rest.java.example.domain.entities.Persona;
import com.avital.api.rest.java.example.domain.usecases.BuscarPersonaPorTelefonoUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonaServiceTest {

    @Mock
    private BuscarPersonaPorTelefonoUseCase buscarPersonaPorTelefonoUseCase;

    @InjectMocks
    private PersonaService personaService;

    private TelefonoRequestDto requestValido;
    private Persona personaEsperada;

    @BeforeEach
    void setUp() {
        requestValido = new TelefonoRequestDto("5551234567");
        personaEsperada = new Persona("5551234567", "Juan", "García", "López");
    }

    @Test
    void buscarPersonaPorTelefono_ConRequestValido_DeberiaRetornarPersona() {
        // Given
        when(buscarPersonaPorTelefonoUseCase.execute("5551234567")).thenReturn(personaEsperada);

        // When
        PersonaResponseDto resultado = personaService.buscarPersonaPorTelefono(requestValido);

        // Then
        assertNotNull(resultado);
        assertEquals("Persona encontrada exitosamente", resultado.getMensaje());
        assertNotNull(resultado.getData());
        assertEquals("Juan", resultado.getData().nombre());
        assertEquals("García", resultado.getData().apellidoPaterno());
        assertEquals("López", resultado.getData().apellidoMaterno());
        assertNotNull(resultado.getTimestamp());

        verify(buscarPersonaPorTelefonoUseCase).execute("5551234567");
    }

    @Test
    void buscarPersonaPorTelefono_ConPersonaNoEncontrada_DeberiaLanzarExcepcion() {
        // Given
        TelefonoRequestDto request = new TelefonoRequestDto("5559999999");
        when(buscarPersonaPorTelefonoUseCase.execute("5559999999"))
                .thenThrow(new PersonaNotFoundException("5559999999"));

        // When & Then
        PersonaNotFoundException excepcion = assertThrows(
                PersonaNotFoundException.class,
                () -> personaService.buscarPersonaPorTelefono(request)
        );

        assertEquals("No se encontró una persona con el número telefónico: 5559999999", excepcion.getMessage());
        assertEquals("PERSONA_NOT_FOUND", excepcion.getErrorCode());
        verify(buscarPersonaPorTelefonoUseCase).execute("5559999999");
    }

    @Test
    void buscarPersonaPorTelefono_ConRequestNulo_DeberiaLanzarIllegalArgumentException() {
        // When & Then
        IllegalArgumentException excepcion = assertThrows(
                IllegalArgumentException.class,
                () -> personaService.buscarPersonaPorTelefono(null)
        );

        assertEquals("La solicitud y el número telefónico son obligatorios", excepcion.getMessage());
        verifyNoInteractions(buscarPersonaPorTelefonoUseCase);
    }

    @Test
    void buscarPersonaPorTelefono_ConNumeroTelefonicoVacio_DeberiaLanzarIllegalArgumentException() {
        // Given
        TelefonoRequestDto requestVacio = new TelefonoRequestDto("");

        // When & Then
        IllegalArgumentException excepcion = assertThrows(
                IllegalArgumentException.class,
                () -> personaService.buscarPersonaPorTelefono(requestVacio)
        );

        assertEquals("La solicitud y el número telefónico son obligatorios", excepcion.getMessage());
        verifyNoInteractions(buscarPersonaPorTelefonoUseCase);
    }

    @Test
    void buscarPersonaPorTelefono_ConPersonaSinApellidoMaterno_DeberiaFuncionar() {
        // Given
        Persona personaSinApellidoMaterno = new Persona("5551234567", "Pedro", "Martínez", null);
        when(buscarPersonaPorTelefonoUseCase.execute("5551234567")).thenReturn(personaSinApellidoMaterno);

        // When
        PersonaResponseDto resultado = personaService.buscarPersonaPorTelefono(requestValido);

        // Then
        assertNotNull(resultado);
        assertEquals("Pedro", resultado.getData().nombre());
        assertEquals("Martínez", resultado.getData().apellidoPaterno());
        assertNull(resultado.getData().apellidoMaterno());
    }
}