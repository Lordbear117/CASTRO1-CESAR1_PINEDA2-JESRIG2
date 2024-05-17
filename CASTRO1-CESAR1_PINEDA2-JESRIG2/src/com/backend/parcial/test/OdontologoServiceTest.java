package com.backend.parcial.test;

import com.backend.parcial.entity.Odontologo;
import com.backend.parcial.repository.impl.OdontologoDaoH2;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.backend.parcial.service.impl.OdontologoService;

class OdontologoServiceTest {

    private OdontologoService odontologoService;

    @Test

    void deberiaRegistrarseUnOdontologoYObtenerElIdCorrespondienteParaOdontologoEnH2(){

        odontologoService = new OdontologoService(new OdontologoDaoH2());
        Odontologo odontologo = new Odontologo( "123456", "Nombre", "Apellido");

        Odontologo odontologoRegistrado = odontologoService.registrarOdontologo(odontologo);

        assertNotNull(odontologoRegistrado.getId());
        assertNotNull(odontologoRegistrado.getNombre());

    }

    @Test
    void deberiaRetornarseUnaListaNoVaciaDeOdontologosEnH2(){
        odontologoService = new OdontologoService(new OdontologoDaoH2());
        assertFalse(odontologoService.listarOdontologos().isEmpty());
    }

}
