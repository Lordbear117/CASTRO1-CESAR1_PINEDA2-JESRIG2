package com.backend.clinica_odontologica.service;

import com.backend.clinica_odontologica.dto.entrada.DomicilioEntradaDto;
import com.backend.clinica_odontologica.dto.entrada.PacienteDtoEntrada;
import com.backend.clinica_odontologica.dto.salida.PacienteDtoSalida;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PacienteServiceTest {

    @Autowired
    IPacienteService pacienteService;

    @Test
    @Order(1)
    void deberiaRegistrarseUnPacienteDeNombreJuan_yRetornarSuId(){

        PacienteDtoEntrada pacienteDtoEntrada = new PacienteDtoEntrada(6845485, "Julio", "Pino", LocalDate.of(2024, 6, 20), new DomicilioEntradaDto("Lomas Mar", 100, "Lomas", "Coyuca"));

        PacienteDtoSalida pacienteDtoSalida = pacienteService.guardarPaciente(pacienteDtoEntrada);

        //assert
        assertNotNull(pacienteDtoSalida);
        assertNotNull(pacienteDtoSalida.getId());
        assertEquals("Julio", pacienteDtoSalida.getNombre());
    }

    @Test
    @Order(2)
    void deberiaDevolverUnaListaNoVaciaDePacientes(){
        List<PacienteDtoSalida> listadoDePacientes = pacienteService.listarTodosLosPacientes();
        assertFalse(listadoDePacientes.isEmpty());
    }

    @Test
    @Order(3)
    void deberiaEliminarseElPacienteConId1(){

        assertDoesNotThrow(() -> pacienteService.eliminarPaciente(1L));
    }
}
