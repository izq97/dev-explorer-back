package org.mesutormanli.devexplorerapi.service.impl;

import java.util.List;

import org.mesutormanli.devexplorerapi.model.entity.Tecnologia;
import org.mesutormanli.devexplorerapi.repository.TecnologiaRepository;
import org.mesutormanli.devexplorerapi.service.TecnologiaService;
import org.springframework.stereotype.Service;

@Service
public class TecnologiaServiceImpl implements TecnologiaService {

    private final TecnologiaRepository repository;

    public TecnologiaServiceImpl(TecnologiaRepository repository) {
        this.repository = repository;
    }


    @Override
    public Tecnologia getTecnologia(Long id) {
        final Tecnologia response = new Tecnologia();
        return repository.findById(id).orElse(response);
    }

    public List<Tecnologia> getAllTecnologias() {
        return repository.findAll();
    }
}

