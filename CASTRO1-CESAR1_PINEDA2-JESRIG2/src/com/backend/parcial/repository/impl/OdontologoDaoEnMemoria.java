package com.backend.parcial.repository.impl;

import com.backend.parcial.entity.Odontologo;
import com.backend.parcial.repository.IDao;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class OdontologoDaoEnMemoria implements IDao<Odontologo> {
    private final Logger LOGGER = Logger.getLogger(OdontologoDaoEnMemoria.class);

    private List<Odontologo> odontologoList =  Arrays.asList(new Odontologo(6L,"6545466", "Fani", "Cabaña"), new Odontologo(7L,"4568545", "Luis", "Donovan"));

    public OdontologoDaoEnMemoria() {
    }

    @Override
    public Odontologo registrar(Odontologo odontologo) {
        odontologoList.add(odontologo);
        LOGGER.info("odontologo guardado en Memoria: " + odontologo);
        return odontologo;
    }

    @Override
    public Odontologo buscarPorId(Long id) {
        Odontologo odontologoBuscado = null;
        int i = 0;
        while(i < odontologoList.size() && odontologoBuscado == null){
            Odontologo odontologo = odontologoList.get(i);
            if(Objects.equals(odontologo.getId(), id)) {
                odontologoBuscado = odontologo;
            }
            i++;
        }
        return odontologoBuscado;
    }

    @Override
    public List<Odontologo> listarTodos() {
        LOGGER.info("Listado de odontologos obtenido desde Memoria: " + odontologoList);
        return odontologoList;
    }

}
