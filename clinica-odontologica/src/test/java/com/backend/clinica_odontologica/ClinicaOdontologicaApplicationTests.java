package com.backend.clinica_odontologica;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ClinicaOdontologicaApplicationTests {

    //Smoke test
    @Test
    void contextLoads() {
        assertNotNull(ClinicaOdontologicaApplication.class);
    }

}
