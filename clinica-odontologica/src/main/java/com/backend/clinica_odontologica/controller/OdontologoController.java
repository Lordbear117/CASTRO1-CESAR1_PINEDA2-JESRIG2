package com.backend.clinica_odontologica.controller;

import com.backend.clinica_odontologica.dto.entrada.OdontologoDtoEntrada;
import com.backend.clinica_odontologica.dto.salida.OdontologoDtoSalida;
import com.backend.clinica_odontologica.exceptions.ResourceNotFoundException;
import com.backend.clinica_odontologica.service.IOdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("odontologos")
public class OdontologoController {

    private final IOdontologoService odontologoService;

    @Autowired
    public OdontologoController(IOdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping("/registrar")
    public OdontologoDtoSalida registrarOdontologo(@RequestBody OdontologoDtoEntrada odontologoDtoEntrada) {
        return odontologoService.guardarOdontologo(odontologoDtoEntrada);
    }

    @GetMapping("/listar")
    public List<OdontologoDtoSalida> listarOdontologos() {
        return odontologoService.listarTodosLosOdontologos();
    }

    @GetMapping("/{id}")
    public OdontologoDtoSalida buscarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        return odontologoService.buscarOdontologo(id);
    }

    @PutMapping("/{id}")
    public OdontologoDtoSalida actualizarOdontologo(@PathVariable Long id, @RequestBody OdontologoDtoEntrada odontologoDtoEntrada) throws ResourceNotFoundException {
        return odontologoService.actualizarOdontologo(id, odontologoDtoEntrada);
    }

    @DeleteMapping("/{id}")
    public void eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo(id);
    }
}
