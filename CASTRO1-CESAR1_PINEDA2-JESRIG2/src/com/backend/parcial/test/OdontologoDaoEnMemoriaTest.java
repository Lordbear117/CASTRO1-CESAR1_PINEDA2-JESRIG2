package com.backend.parcial.test;

import com.backend.parcial.entity.Odontologo;
import com.backend.parcial.repository.impl.OdontologoDaoEnMemoria;
import com.backend.parcial.service.impl.OdontologoService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoDaoEnMemoriaTest {

    private OdontologoService odontologoService;

    @Test
    void deberiaRetonarUnaListaNoVaciaDeOdontologoEnMemoria(){

        odontologoService = new OdontologoService(new OdontologoDaoEnMemoria());
        assertFalse(odontologoService.listarOdontologos().isEmpty());

    }

}
