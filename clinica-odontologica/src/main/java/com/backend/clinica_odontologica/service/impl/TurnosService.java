package com.backend.clinica_odontologica.service.impl;

import com.backend.clinica_odontologica.dto.entrada.TurnoDtoEntrada;
import com.backend.clinica_odontologica.dto.salida.DomicilioDtoSalida;
import com.backend.clinica_odontologica.dto.salida.OdontologoDtoSalida;
import com.backend.clinica_odontologica.dto.salida.PacienteDtoSalida;
import com.backend.clinica_odontologica.dto.salida.TurnoDtoSalida;
import com.backend.clinica_odontologica.entity.Odontologo;
import com.backend.clinica_odontologica.entity.Paciente;
import com.backend.clinica_odontologica.entity.Turno;
import com.backend.clinica_odontologica.exceptions.ResourceNotFoundException;
import com.backend.clinica_odontologica.repository.TurnoRepository;
import com.backend.clinica_odontologica.service.ITurnoService;
import com.backend.clinica_odontologica.utils.JsonPrinter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TurnosService implements ITurnoService {

    private static final Logger LOGGER = Logger.getLogger(TurnosService.class);
    private final TurnoRepository turnoRepository;
    private final OdontologoService odontologoService;
    private final PacienteService pacienteService;
    private final ModelMapper modelMapper;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public TurnosService(TurnoRepository turnoRepository, OdontologoService odontologoService, PacienteService pacienteService, ModelMapper modelMapper) {
        this.turnoRepository = turnoRepository;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
        this.modelMapper = modelMapper;
    }

    @Override
    public TurnoDtoSalida buscarTurno(Long id) throws ResourceNotFoundException {
        Turno turno = turnoRepository.findById(id).orElse(null);
        if (turno == null) {
            throw new ResourceNotFoundException("No existe el registro con turno ID: " + id);
        }
        LOGGER.info("Turno encontrado: " + JsonPrinter.toString(turno));
        return convertToDto(turno);
    }

    @Override
    @Transactional
    public TurnoDtoSalida guardarTurno(TurnoDtoEntrada turnoDtoEntrada) throws ResourceNotFoundException {
        Turno turnoAGuardar = new Turno();
        turnoAGuardar.setFechaYHora(turnoDtoEntrada.getFechaYHora());

        OdontologoDtoSalida odontologoDto = odontologoService.buscarOdontologo(turnoDtoEntrada.getOdontologoId());

        PacienteDtoSalida pacienteDto = pacienteService.buscarPaciente(turnoDtoEntrada.getPacienteId());

        Odontologo odontologo = modelMapper.map(odontologoDto, Odontologo.class);
        Paciente paciente = modelMapper.map(pacienteDto, Paciente.class);

        odontologo = entityManager.merge(odontologo);
        paciente = entityManager.merge(paciente);

        turnoAGuardar.setOdontologo(odontologo);
        turnoAGuardar.setPaciente(paciente);

        Turno turnoGuardado = turnoRepository.save(turnoAGuardar);
        LOGGER.info("Turno guardado: " + JsonPrinter.toString(turnoGuardado));
        return convertToDto(turnoGuardado);
    }

    @Override
    public List<TurnoDtoSalida> listarTodosLosTurnos() {
        List<TurnoDtoSalida> turnos = turnoRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .toList();

        LOGGER.info("Listado de todos los turnos: {}" + JsonPrinter.toString(turnos));

        if (turnos.isEmpty()) {
            LOGGER.warn("No se encontraron turnos");
        }
        return turnos;
    }

    @Override
    public TurnoDtoSalida actualizarTurno(Long id, TurnoDtoEntrada turnoDtoEntrada) throws ResourceNotFoundException {
        Turno turnoExistente = turnoRepository.findById(id).orElse(null);
        if (turnoExistente == null) {
            throw new ResourceNotFoundException("No existe el registro con turno ID: " + id);
        }

        turnoExistente.setFechaYHora(turnoDtoEntrada.getFechaYHora());

        OdontologoDtoSalida odontologo = odontologoService.buscarOdontologo(turnoDtoEntrada.getOdontologoId());

        turnoExistente.setOdontologo(modelMapper.map(odontologo, Odontologo.class));

        PacienteDtoSalida paciente = pacienteService.buscarPaciente(turnoDtoEntrada.getPacienteId());

        turnoExistente.setPaciente(modelMapper.map(paciente, Paciente.class));

        turnoExistente = turnoRepository.save(turnoExistente);
        LOGGER.info("Turno actualizado con éxito: " + JsonPrinter.toString(turnoExistente));
        return convertToDto(turnoExistente);
    }

    @Override
    public void eliminarTurno(Long id) throws ResourceNotFoundException {
        if (turnoRepository.existsById(id)) {
            turnoRepository.deleteById(id);
        } else {
            LOGGER.warn("No se encontró el turno con ID: " + id);
            throw new ResourceNotFoundException("No existe el registro con turno ID: " + id);
        }
    }

    private TurnoDtoSalida convertToDto(Turno turno) {
        TurnoDtoSalida turnoDtoSalida = modelMapper.map(turno, TurnoDtoSalida.class);
        OdontologoDtoSalida odontologoDtoSalida = modelMapper.map(turno.getOdontologo(), OdontologoDtoSalida.class);
        DomicilioDtoSalida domicilioDtoSalida = modelMapper.map(turno.getPaciente().getDomicilio(), DomicilioDtoSalida.class);
        PacienteDtoSalida pacienteDtoSalida = modelMapper.map(turno.getPaciente(), PacienteDtoSalida.class);
        pacienteDtoSalida.setDomicilioDtoSalida(domicilioDtoSalida);

        turnoDtoSalida.setOdontologoDtoSalida(odontologoDtoSalida);
        turnoDtoSalida.setPacienteDtoSalida(pacienteDtoSalida);

        return turnoDtoSalida;
    }

}
