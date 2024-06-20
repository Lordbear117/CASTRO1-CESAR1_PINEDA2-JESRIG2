package com.backend.clinica_odontologica.service;

import com.backend.clinica_odontologica.dto.entrada.PacienteDtoEntrada;
import com.backend.clinica_odontologica.dto.entrada.OdontologoDtoEntrada;
import com.backend.clinica_odontologica.dto.entrada.TurnoDtoEntrada;
import com.backend.clinica_odontologica.dto.entrada.DomicilioEntradaDto;
import com.backend.clinica_odontologica.dto.salida.TurnoDtoSalida;
import com.backend.clinica_odontologica.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@PropertySource("classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TurnosServiceTest {
    @Autowired
    IPacienteService pacienteService;

    @Autowired
    IOdontologoService odontologoService;

    @Autowired
    ITurnoService turnosService;

    @Test
    @Order(1)
    void deberiaGuardarUnTurnoConFecha20240620T1500_yRetornarSuId() throws ResourceNotFoundException {
        PacienteDtoEntrada pacienteDtoEntrada = new PacienteDtoEntrada(1001L, "Luis", "Garcia", LocalDate.of(2024, 6, 22), new DomicilioEntradaDto("Calle", 123, "Localidad", "Provincia"));

        pacienteService.guardarPaciente(pacienteDtoEntrada);
        OdontologoDtoEntrada odontologoDtoEntrada = new OdontologoDtoEntrada(10001L,"Luis", "Salas");

        odontologoService.guardarOdontologo(odontologoDtoEntrada);
        TurnoDtoEntrada turnoDtoEntrada = new TurnoDtoEntrada(LocalDateTime.of(2024, 06, 20, 15,00),1L, 1L);

        TurnoDtoSalida turnoDtoSalida = turnosService.guardarTurno(turnoDtoEntrada);

        //assert
        assertNotNull(turnoDtoSalida);
        assertNotNull(turnoDtoSalida.getId());
        assertEquals("2024-06-20T15:00", turnoDtoSalida.getFechaHora().toString());
    }

    @Test
    @Order(2)
    void deberiaDevolverUnaListaNoVaciaDeTurnos(){
        List<TurnoDtoSalida> listadoDeTurnos = turnosService.listarTodosLosTurnos();
        assertFalse(listadoDeTurnos.isEmpty());
    }

    @Test
    @Order(3)
    void deberiaEliminarseElTurnoConId1(){

        assertDoesNotThrow(() -> turnosService.eliminarTurno(1L));
    }

    @Test
    @Order(4)
    void deberiaDevolverUnaListaVaciaDeTurnos(){
        List<TurnoDtoSalida> listadoDeTurnos = turnosService.listarTodosLosTurnos();
        assertTrue(listadoDeTurnos.isEmpty());
    }
}
