package com.backend.clinica_odontologica.dto.entrada;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TurnoDtoEntrada {
    private Long id;

    private LocalDateTime fechaYHora;
    
    private Long odontologoId;
    
    private Long pacienteId;

    public TurnoDtoEntrada(LocalDateTime fechaYHora, Long odontologoId, Long pacienteId) {
        this.fechaYHora = fechaYHora;
        this.odontologoId = odontologoId;
        this.pacienteId = pacienteId;
    }
}
