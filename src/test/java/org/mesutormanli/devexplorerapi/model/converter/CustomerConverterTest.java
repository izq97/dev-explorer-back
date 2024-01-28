package org.mesutormanli.devexplorerapi.model.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mesutormanli.devexplorerapi.base.BaseServiceTest;
import org.mesutormanli.devexplorerapi.model.converter.CustomerConverter;
import org.mesutormanli.devexplorerapi.model.dto.CustomerDto;
import org.mesutormanli.devexplorerapi.model.entity.CustomerEntity;

import static org.junit.jupiter.api.Assertions.assertNull;

class CustomerConverterTest extends BaseServiceTest {

    private CustomerConverter devexplorerConverter;

    @BeforeEach
    void setUp() {
        devexplorerConverter = new CustomerConverter();
    }

    @Test
    void toDto_null() {
        final CustomerDto devexplorerDto = devexplorerConverter.toDto(null);
        assertNull(devexplorerDto);
    }

    @Test
    void toEntity_null() {
        final CustomerEntity devexplorerEntity = devexplorerConverter.toEntity(null);
        assertNull(devexplorerEntity);
    }

}