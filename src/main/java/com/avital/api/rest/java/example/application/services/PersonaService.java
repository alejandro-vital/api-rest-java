package com.avital.api.rest.java.example.application.services;

import com.avital.api.rest.java.example.application.dto.PersonaDataDto;
import com.avital.api.rest.java.example.application.dto.PersonaResponseDto;
import com.avital.api.rest.java.example.application.dto.TelefonoRequestDto;
import com.avital.api.rest.java.example.domain.entities.Persona;
import com.avital.api.rest.java.example.domain.usecases.BuscarPersonaPorTelefonoUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class PersonaService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonaService.class);
    private final BuscarPersonaPorTelefonoUseCase buscarPersonaPorTelefonoUseCase;

    public PersonaService(BuscarPersonaPorTelefonoUseCase buscarPersonaPorTelefonoUseCase) {
        this.buscarPersonaPorTelefonoUseCase = buscarPersonaPorTelefonoUseCase;
    }

    public PersonaResponseDto buscarPersonaPorTelefono(TelefonoRequestDto request) {
        validateRequest(request);

        LOGGER.info("Procesando solicitud de búsqueda para teléfono: {}", request.numeroTelefonico());

        Persona persona = buscarPersonaPorTelefonoUseCase.execute(request.numeroTelefonico());
        PersonaResponseDto response = mapToResponseDto(persona);

        LOGGER.info("Persona encontrada exitosamente: {}", persona.getNombre());
        return response;
    }

    private void validateRequest(TelefonoRequestDto request) {
        if (request == null || !StringUtils.hasText(request.numeroTelefonico())) {
            throw new IllegalArgumentException("La solicitud y el número telefónico son obligatorios");
        }
    }

    private PersonaResponseDto mapToResponseDto(Persona persona) {
        PersonaDataDto data = new PersonaDataDto(
                persona.getNombre(),
                persona.getApellidoPaterno(),
                persona.getApellidoMaterno()
        );

        return new PersonaResponseDto("Persona encontrada exitosamente", data);
    }
}