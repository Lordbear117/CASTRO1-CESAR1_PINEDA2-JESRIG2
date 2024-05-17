package com.backend.parcial.test;

import com.backend.parcial.entity.Domicilio;
import com.backend.parcial.entity.Paciente;
import com.backend.parcial.repository.impl.PacienteDaoH2;
import com.backend.parcial.service.impl.PacienteService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PacienteServiceTest {

    private PacienteService pacienteService;

    @Test
    void deberiaRegistrarseUnPacienteYObtenerElIdCorrespondienteParaPacienteYDomicilioEnH2(){

        pacienteService = new PacienteService(new PacienteDaoH2());
        Paciente paciente = new Paciente("Nombre", "Apellido", 123456, LocalDate.of(2023, 05, 02), new Domicilio("Calle", 13, "Localidad", "Provincia"));

        Paciente pacienteRegistrado = pacienteService.registrarPaciente(paciente);

        assertNotNull(pacienteRegistrado.getDomicilio().getId());
        assertNotNull(pacienteRegistrado.getId());

    }

    @Test
    void deberiaRetornarseUnaListaNoVaciaDePacientesEnH2(){
        pacienteService = new PacienteService(new PacienteDaoH2());
        assertFalse(pacienteService.listarPacientes().isEmpty());
    }

}