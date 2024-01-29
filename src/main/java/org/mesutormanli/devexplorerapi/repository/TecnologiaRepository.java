package org.mesutormanli.devexplorerapi.repository;

import org.mesutormanli.devexplorerapi.model.entity.Tecnologia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TecnologiaRepository extends JpaRepository<Tecnologia, Long> {
    
}
