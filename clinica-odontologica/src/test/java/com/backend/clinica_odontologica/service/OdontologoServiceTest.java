package com.backend.clinica_odontologica.service;

import com.backend.clinica_odontologica.dto.entrada.OdontologoDtoEntrada;
import com.backend.clinica_odontologica.dto.salida.OdontologoDtoSalida;
import com.backend.clinica_odontologica.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@PropertySource("classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OdontologoServiceTest {

    @Autowired
    IOdontologoService odontologoService;

    @Test
    @Order(1)
    void deberiaGuardarUnOdontologoExitosamente() {
        OdontologoDtoEntrada odontologoDtoEntrada = new OdontologoDtoEntrada(10001L, "Cesar", "Castro");
        OdontologoDtoSalida odontologoGuardado = odontologoService.guardarOdontologo(odontologoDtoEntrada);
        assertNotNull(odontologoGuardado.getId());
    }

    @Test
    @Order(2)
    void deberiaBuscarUnOdontologoExitosamente() {
        assertDoesNotThrow(() -> odontologoService.buscarOdontologo(1L));
    }

    @Test
    @Order(3)
    void deberiaFallarAlBuscarUnOdontologoNoExistente() {
        assertThrows(ResourceNotFoundException.class, () -> odontologoService.buscarOdontologo(9999L));
    }

    @Test
    @Order(4)
    void deberiaListarOdontologosExistentes() {
        assertFalse(odontologoService.listarTodosLosOdontologos().isEmpty());
    }
}

