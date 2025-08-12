package com.avital.api.rest.java.example.domain.repositories;

import com.avital.api.rest.java.example.domain.entities.Persona;

import java.util.Optional;

public interface IPersonaRepository {

    Optional<Persona> findByNumeroTelefonico(String numeroTelefonico);

}
