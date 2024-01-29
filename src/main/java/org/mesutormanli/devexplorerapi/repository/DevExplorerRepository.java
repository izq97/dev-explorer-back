package org.mesutormanli.devexplorerapi.repository;

import org.mesutormanli.devexplorerapi.model.entity.DevExplorer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevExplorerRepository extends JpaRepository<DevExplorer, Long> {
    
}
