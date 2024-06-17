package com.backend.clinica_odontologica.service;

import com.backend.clinica_odontologica.dto.entrada.TurnoDtoEntrada;
import com.backend.clinica_odontologica.dto.salida.TurnoDtoSalida;
import com.backend.clinica_odontologica.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ITurnoService {
    TurnoDtoSalida buscarTurno(Long id) throws ResourceNotFoundException;

    TurnoDtoSalida guardarTurno(TurnoDtoEntrada turno) throws ResourceNotFoundException;

    List<TurnoDtoSalida> listarTodosLosTurnos();

    TurnoDtoSalida actualizarTurno(Long id, TurnoDtoEntrada turnoDtoEntrada) throws ResourceNotFoundException;

    void eliminarTurno(Long id) throws ResourceNotFoundException;
}
