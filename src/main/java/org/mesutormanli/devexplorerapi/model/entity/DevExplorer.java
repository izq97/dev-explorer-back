package org.mesutormanli.devexplorerapi.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DEVEXPLORER")
public class DevExplorer {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String nombre;

    @Column
    private String descripcion;
}