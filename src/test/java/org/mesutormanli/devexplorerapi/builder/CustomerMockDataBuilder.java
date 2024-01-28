package org.mesutormanli.devexplorerapi.builder;

import org.mesutormanli.devexplorerapi.model.dto.CustomerDto;
import org.mesutormanli.devexplorerapi.model.request.CustomerRequest;
import org.mesutormanli.devexplorerapi.model.response.CustomerDeleteResponse;
import org.mesutormanli.devexplorerapi.model.response.CustomerListResponse;

import java.util.Collections;

public class CustomerMockDataBuilder {

    private CustomerMockDataBuilder() {
    }

    public static CustomerListResponse generateCustomerListResponse(long devexplorerId) {
        return CustomerListResponse.builder()
                .customers(Collections.singletonList(generateCustomerDto(devexplorerId)))
                .build();
    }

    public static CustomerRequest generateCustomerRequest() {
        return GenericMockDataBuilder.of(CustomerRequest.class).build();
    }

    public static CustomerDeleteResponse generateCustomerDeleteResponse() {
        return CustomerDeleteResponse.builder()
                .deletedCustomerCount(1L)
                .build();
    }

    public static CustomerDto generateCustomerDto(long devexplorerId) {
        final CustomerDto dto = GenericMockDataBuilder.of(CustomerDto.class)
                .excludeField("id")
                .build();
        dto.setId(devexplorerId);
        return dto;
    }

}
