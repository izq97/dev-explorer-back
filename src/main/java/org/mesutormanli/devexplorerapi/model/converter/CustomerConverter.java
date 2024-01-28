package org.mesutormanli.devexplorerapi.model.converter;

import org.mesutormanli.devexplorerapi.model.dto.CustomerDto;
import org.mesutormanli.devexplorerapi.model.entity.CustomerEntity;
import org.mesutormanli.devexplorerapi.model.request.CustomerRequest;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter {
    public CustomerDto toDto(CustomerEntity entity) {
        if (entity == null) {
            return null;
        } else {
            return CustomerDto.builder()
                    .id(entity.getId())
                    .nombre(entity.getNombre())
                    .age(entity.getAge())
                    .tecnologiaVotada(entity.getTecnologiaVotada())
                    .build();
        }
    }

    public CustomerEntity toEntity(CustomerRequest request) {
        if (request == null) {
            return null;
        } else {
            return CustomerEntity.builder()
                    .nombre(request.getNombre())
                    .age(request.getAge())
                    .tecnologiaVotada(request.getTecnologiaVotada())
                    .build();
        }
    }

}
