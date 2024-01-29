package org.mesutormanli.devexplorerapi.service;

import java.util.List;

import org.mesutormanli.devexplorerapi.model.entity.Tecnologia;

public interface TecnologiaService {

    Tecnologia getTecnologia(Long id);
    List<Tecnologia> getAllTecnologias();
}
