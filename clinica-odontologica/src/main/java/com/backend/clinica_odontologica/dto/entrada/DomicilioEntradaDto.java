package com.backend.clinica_odontologica.dto.entrada;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DomicilioEntradaDto {
    
    @NotBlank(message = "Debe proveerse el nombre de la calle del domicilio")
    private String calle;
    
    @Positive(message = "El numero no puede ser nulo o menor a cero")
    @Digits(integer = 8, fraction = 0, message = "El número debe tener como máximo 8 dígitos")
    private int numero;
    
    @NotBlank(message = "Debe proveerse la localidad del domicilio")
    @Size(max = 50, message = "La localidad debe tener maxino 50 caracteres")
    private String localidad;
   
    @NotBlank(message = "Debe proveerse la provincia del domicilio")
    @Size(max = 50, message = "La provincia debe tener maxino 50 caracteres")
    private String provincia;

    public DomicilioEntradaDto(String calle, int numero, String localidad, String provincia) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }

}
