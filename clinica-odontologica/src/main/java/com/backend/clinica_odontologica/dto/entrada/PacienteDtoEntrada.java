package com.backend.clinica_odontologica.dto.entrada;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
public class PacienteDtoEntrada {
    
    @NotNull(message = "Debe especificarse el dni del paciente")
    private long dni;
    
    @NotBlank(message = "Debe especificarse el nombre del paciente")
    @Size(max = 50, message = "El nombre debe tener maximo 50 caracteres")
    private String nombre;

    @NotBlank(message = "Debe especificarse el apellido del paciente")
    @Size(max = 50, message = "El apellido debe tener maximo 50 caracteres")
    private String apellido;

    @FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
    @NotNull(message = "Debe especificarse la fecha de ingreso del paciente")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaAlta;
    
    private DomicilioEntradaDto domicilioEntradaDto;

    public PacienteDtoEntrada(long dni, String nombre, String apellido, LocalDate fechaAlta, DomicilioEntradaDto domicilioEntradaDto) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaAlta = fechaAlta;
        this.domicilioEntradaDto = domicilioEntradaDto;
    }
}
