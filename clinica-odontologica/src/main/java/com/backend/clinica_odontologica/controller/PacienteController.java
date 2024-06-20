package com.backend.clinica_odontologica.controller;

import com.backend.clinica_odontologica.dto.entrada.PacienteDtoEntrada;
import com.backend.clinica_odontologica.dto.salida.PacienteDtoSalida;
import com.backend.clinica_odontologica.exceptions.ResourceNotFoundException;
import com.backend.clinica_odontologica.service.IPacienteService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("pacientes")
public class PacienteController {

    private IPacienteService pacienteService;

    @Autowired
    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<PacienteDtoSalida> registarPaciente(
            @RequestBody @Valid 
    PacienteDtoEntrada pacienteDtoEntrada) {
        return new ResponseEntity<>(pacienteService.guardarPaciente(pacienteDtoEntrada), HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<PacienteDtoSalida>> listarPacientes() {
        return new ResponseEntity<>(pacienteService.listarTodosLosPacientes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDtoSalida> buscarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(pacienteService.buscarPaciente(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDtoSalida> actualizarPaciente(@PathVariable Long id,
            @RequestBody @Valid 
    PacienteDtoEntrada pacienteDtoEntrada) throws ResourceNotFoundException {
        return new ResponseEntity<>(pacienteService.actualizarPaciente(id, pacienteDtoEntrada), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(id);
        return new ResponseEntity<>("Paciente eliminado correctamente", HttpStatus.NO_CONTENT);
    }
}
