package org.mesutormanli.devexplorerapi.model.request;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class CustomerRequest implements Serializable {
    private String nombre;
    private Integer age;
    private String tecnologiaVotada;
}
