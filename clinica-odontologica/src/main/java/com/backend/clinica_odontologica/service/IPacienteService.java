package com.backend.clinica_odontologica.service;

import com.backend.clinica_odontologica.dto.entrada.PacienteDtoEntrada;
import com.backend.clinica_odontologica.dto.salida.PacienteDtoSalida;
import com.backend.clinica_odontologica.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPacienteService {
    PacienteDtoSalida buscarPaciente(Long id) throws ResourceNotFoundException;

    PacienteDtoSalida guardarPaciente(PacienteDtoEntrada pacienteDtoEntrada);

    List<PacienteDtoSalida> listarTodosLosPacientes();

    PacienteDtoSalida actualizarPaciente(Long id, PacienteDtoEntrada pacienteDtoEntrada) throws ResourceNotFoundException;

    void eliminarPaciente(Long id) throws ResourceNotFoundException;
}
