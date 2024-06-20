package com.backend.clinica_odontologica.controller;

import com.backend.clinica_odontologica.dto.entrada.OdontologoDtoEntrada;
import com.backend.clinica_odontologica.dto.salida.OdontologoDtoSalida;
import com.backend.clinica_odontologica.exceptions.ResourceNotFoundException;
import com.backend.clinica_odontologica.service.IOdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("odontologos")
public class OdontologoController {

    private final IOdontologoService odontologoService;

    @Autowired
    public OdontologoController(IOdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<OdontologoDtoSalida> registrarOdontologo(
            @RequestBody @Valid 
    OdontologoDtoEntrada odontologoDtoEntrada) {
        return new ResponseEntity<>(odontologoService.guardarOdontologo(odontologoDtoEntrada), HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<OdontologoDtoSalida>> listarOdontologos() {
        return new ResponseEntity<>(odontologoService.listarTodosLosOdontologos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OdontologoDtoSalida> buscarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(odontologoService.buscarOdontologo(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OdontologoDtoSalida> actualizarOdontologo(@PathVariable Long id,
            @RequestBody @Valid OdontologoDtoEntrada odontologoDtoEntrada)
    throws ResourceNotFoundException {
        return new ResponseEntity<>(odontologoService.actualizarOdontologo(id, odontologoDtoEntrada), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo(id);
        return new ResponseEntity<>("Odontologo eliminado correctamente", HttpStatus.NO_CONTENT);
    }
}
