package com.avital.api.rest.java.example.infrastructure.controllers;

import com.avital.api.rest.java.example.application.dto.TelefonoRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PersonaControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void buscarPersona_ConNumeroExistente_DeberiaRetornar200() throws Exception {
        // Given
        TelefonoRequestDto request = new TelefonoRequestDto("5551234567");

        // When & Then
        mockMvc.perform(post("/api/personas/buscar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.mensaje").value("Persona encontrada exitosamente"))
                .andExpect(jsonPath("$.data.nombre").value("Juan"))
                .andExpect(jsonPath("$.data.apellidoPaterno").value("García"))
                .andExpect(jsonPath("$.data.apellidoMaterno").value("López"))
                .andExpect(jsonPath("$.fecha_hora").exists());
    }

    @Test
    void buscarPersona_ConNumeroNoExistente_DeberiaRetornar404() throws Exception {
        // Given
        TelefonoRequestDto request = new TelefonoRequestDto("5559999999");

        // When & Then
        mockMvc.perform(post("/api/personas/buscar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error").value("Persona no encontrada"))
                .andExpect(jsonPath("$.codigo").value("PERSONA_NOT_FOUND"))
                .andExpect(jsonPath("$.mensaje").value("No se encontró una persona con el número telefónico: 5559999999"))
                .andExpect(jsonPath("$.fecha_hora").exists());
    }

    @Test
    void buscarPersona_ConNumeroInvalido_DeberiaRetornar400() throws Exception {
        // Given
        TelefonoRequestDto request = new TelefonoRequestDto("123");

        // When & Then
        mockMvc.perform(post("/api/personas/buscar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error").value("Datos de entrada inválidos"))
                .andExpect(jsonPath("$.codigo").value("VALIDATION_ERROR"))
                .andExpect(jsonPath("$.mensaje").value("El número telefónico debe tener 10 dígitos y/o contener solo números"));
    }

    @Test
    void buscarPersona_ConNumeroVacio_DeberiaRetornar400() throws Exception {
        // Given
        TelefonoRequestDto request = new TelefonoRequestDto("");

        // When & Then
        mockMvc.perform(post("/api/personas/buscar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error").value("Datos de entrada inválidos"))
                .andExpect(jsonPath("$.codigo").value("VALIDATION_ERROR"));
    }

    @Test
    void buscarPersona_ConLetrasEnNumero_DeberiaRetornar400() throws Exception {
        // Given
        TelefonoRequestDto request = new TelefonoRequestDto("555abc1234");

        // When & Then
        mockMvc.perform(post("/api/personas/buscar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error").value("Datos de entrada inválidos"))
                .andExpect(jsonPath("$.codigo").value("VALIDATION_ERROR"));
    }

    @Test
    void buscarPersona_ConBodyVacio_DeberiaRetornar400() throws Exception {
        // When & Then
        mockMvc.perform(post("/api/personas/buscar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error").value("Datos de entrada inválidos"))
                .andExpect(jsonPath("$.codigo").value("VALIDATION_ERROR"));
    }

    @Test
    void buscarPersona_TodasLasPersonasExistentes() throws Exception {
        // Test para Juan García López
        TelefonoRequestDto request1 = new TelefonoRequestDto("5551234567");
        mockMvc.perform(post("/api/personas/buscar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.nombre").value("Juan"));

        // Test para María Rodríguez Martínez
        TelefonoRequestDto request2 = new TelefonoRequestDto("5552345678");
        mockMvc.perform(post("/api/personas/buscar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.nombre").value("María"));

        // Test para Carlos Fernández González
        TelefonoRequestDto request3 = new TelefonoRequestDto("5553456789");
        mockMvc.perform(post("/api/personas/buscar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request3)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.nombre").value("Carlos"));

        // Test para Ana Morales Jiménez
        TelefonoRequestDto request4 = new TelefonoRequestDto("5554567890");
        mockMvc.perform(post("/api/personas/buscar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request4)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.nombre").value("Ana"));
    }
}