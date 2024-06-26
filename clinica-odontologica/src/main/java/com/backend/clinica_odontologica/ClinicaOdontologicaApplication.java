package com.backend.clinica_odontologica;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClinicaOdontologicaApplication {

    private static final Logger LOGGER = Logger.getLogger(ClinicaOdontologicaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ClinicaOdontologicaApplication.class, args);
        LOGGER.info("Application is running");
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
