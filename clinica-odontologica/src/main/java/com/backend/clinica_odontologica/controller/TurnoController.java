package com.backend.clinica_odontologica.controller;

import com.backend.clinica_odontologica.dto.entrada.TurnoDtoEntrada;
import com.backend.clinica_odontologica.dto.salida.TurnoDtoSalida;
import com.backend.clinica_odontologica.exceptions.ResourceNotFoundException;
import com.backend.clinica_odontologica.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("turnos")
public class TurnoController {

    private ITurnoService turnoService;

    @Autowired
    public TurnoController(ITurnoService turnoService) { this.turnoService = turnoService; }

    @PostMapping("/registrar")
    public TurnoDtoSalida registarTurno(@RequestBody TurnoDtoEntrada turnoDtoEntrada) throws ResourceNotFoundException {
        return turnoService.guardarTurno(turnoDtoEntrada);
    }

    @GetMapping("/listar")
    public List<TurnoDtoSalida> listarTurnos() {
        return turnoService.listarTodosLosTurnos();
    }

    @GetMapping("/{id}")
    public TurnoDtoSalida buscarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        return turnoService.buscarTurno(id);
    }

    @PutMapping("/{id}")
    public TurnoDtoSalida actualizarTurno(@PathVariable Long id, @RequestBody TurnoDtoEntrada turnoDtoEntrada) throws ResourceNotFoundException {
        return turnoService.actualizarTurno(id, turnoDtoEntrada);
    }

    @DeleteMapping("/{id}")
    public void eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        turnoService.eliminarTurno(id);
    }
}
