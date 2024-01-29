package org.mesutormanli.devexplorerapi.service.impl;

import org.mesutormanli.devexplorerapi.model.entity.DevExplorer;
import org.mesutormanli.devexplorerapi.repository.DevExplorerRepository;
import org.mesutormanli.devexplorerapi.service.DevExplorerService;
import org.springframework.stereotype.Service;

@Service
public class DevExplorerServiceImpl implements DevExplorerService {

    private final DevExplorerRepository repository;

    public DevExplorerServiceImpl(DevExplorerRepository repository) {
        this.repository = repository;
    }


    @Override
    public DevExplorer getDevExplorer(Long id) {
        final DevExplorer response = new DevExplorer();
        return repository.findById(id).orElse(response);
    }

}

