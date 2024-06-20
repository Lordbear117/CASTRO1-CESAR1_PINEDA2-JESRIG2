package com.backend.clinica_odontologica.dto.entrada;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OdontologoDtoEntrada {

    @NotNull(message = "Debe especificarse la matricula del odontolgo")
    private Long numMatricula;
    
    @NotBlank(message = "Debe especificarse el nombre del odontolgo")
    @Size(max = 50, message = "El nombre debe tener maximo 50 caracteres")
    private String nombre;
    
    @NotBlank(message = "Debe especificarse el apellido del odontolgo")
    @Size(max = 50, message = "El nombre debe tener maxino 50 caracteres")
    private String apellido;

    public OdontologoDtoEntrada(Long numMatricula, String nombre, String apellido) {
        this.numMatricula = numMatricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }
}
