package com.backend.clinica_odontologica.controller;

import com.backend.clinica_odontologica.dto.entrada.PacienteDtoEntrada;
import com.backend.clinica_odontologica.dto.salida.PacienteDtoSalida;
import com.backend.clinica_odontologica.exceptions.ResourceNotFoundException;
import com.backend.clinica_odontologica.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    private IPacienteService pacienteService;

    @Autowired
    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping("/registrar")
    public PacienteDtoSalida registarPaciente(@RequestBody PacienteDtoEntrada pacienteDtoEntrada) {
        return pacienteService.guardarPaciente(pacienteDtoEntrada);
    }

    @GetMapping("/listar")
    public List<PacienteDtoSalida> listarPacientes() {
        return pacienteService.listarTodosLosPacientes();
    }

    @GetMapping("/{id}")
    public PacienteDtoSalida buscarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        return pacienteService.buscarPaciente(id);
    }

    @PutMapping("/{id}")
    public PacienteDtoSalida actualizarPaciente(@PathVariable Long id, @RequestBody PacienteDtoEntrada pacienteDtoEntrada) throws ResourceNotFoundException {
        return pacienteService.actualizarPaciente(id, pacienteDtoEntrada);
    }

    @DeleteMapping("/{id}")
    public void eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(id);
    }
}
