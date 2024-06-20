package com.backend.clinica_odontologica.service;

import com.backend.clinica_odontologica.dto.entrada.DomicilioEntradaDto;
import com.backend.clinica_odontologica.dto.entrada.OdontologoDtoEntrada;
import com.backend.clinica_odontologica.dto.entrada.PacienteDtoEntrada;
import com.backend.clinica_odontologica.dto.entrada.TurnoDtoEntrada;
import com.backend.clinica_odontologica.dto.salida.TurnoDtoSalida;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TurnosServiceTest {
    @Autowired
    IPacienteService pacienteService;

    @Autowired
    IOdontologoService odontologoService;

    @Autowired
    ITurnoService turnosService;

    @Test
    @Order(1)
    void deberiaDevolverUnaListaNoVaciaDeTurnos(){
        List<TurnoDtoSalida> listadoDeTurnos = turnosService.listarTodosLosTurnos();
        assertFalse(listadoDeTurnos.isEmpty());
    }

    @Test
    @Order(2)
    void deberiaEliminarseElTurnoConId1(){

        assertDoesNotThrow(() -> turnosService.eliminarTurno(1L));
    }

    @Test
    @Order(3)
    void deberiaDevolverUnaListaVaciaDeTurnos(){
        List<TurnoDtoSalida> listadoDeTurnos = turnosService.listarTodosLosTurnos();
        assertTrue(listadoDeTurnos.isEmpty());
    }
}
