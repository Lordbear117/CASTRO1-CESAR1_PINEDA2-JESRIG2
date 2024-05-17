package com.backend.parcial.service;

import com.backend.parcial.entity.Paciente;

import java.util.List;

public interface  IPacienteService {
    Paciente registrarPaciente(Paciente paciente);
    List<Paciente> listarPacientes();
}
