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
@Table(name = "TECNOLOGIA")
public class Tecnologia {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String nombreLenguaje;

    @Column
    private String tipo; // Backend o Frontend

    @Column
    private String descripcioón;
}