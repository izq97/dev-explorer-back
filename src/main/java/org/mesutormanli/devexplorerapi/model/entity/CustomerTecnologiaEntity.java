package org.mesutormanli.devexplorerapi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CUSTOMER_TECNOLOGIA")
public class CustomerTecnologiaEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "tecnologia_id")
    private Tecnologia tecnologia;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_votacion")
    private Date fechaVotacion;
}
