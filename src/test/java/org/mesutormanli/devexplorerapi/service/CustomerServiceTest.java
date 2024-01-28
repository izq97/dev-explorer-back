package org.mesutormanli.devexplorerapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mesutormanli.devexplorerapi.base.BaseServiceTest;
import org.mesutormanli.devexplorerapi.model.converter.CustomerConverter;
import org.mesutormanli.devexplorerapi.model.dto.CustomerDto;
import org.mesutormanli.devexplorerapi.model.entity.CustomerEntity;
import org.mesutormanli.devexplorerapi.model.request.CustomerRequest;
import org.mesutormanli.devexplorerapi.model.response.CustomerDeleteResponse;
import org.mesutormanli.devexplorerapi.model.response.CustomerListResponse;
import org.mesutormanli.devexplorerapi.repository.CustomerRepository;
import org.mesutormanli.devexplorerapi.service.CustomerService;
import org.mesutormanli.devexplorerapi.service.impl.CustomerServiceImpl;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mesutormanli.devexplorerapi.builder.CustomerMockDataBuilder.generateCustomerRequest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


class CustomerServiceTest extends BaseServiceTest {

    private static final long CUSTOMER_ID = 1;
    private CustomerListResponse devexplorerListResponse;
    private CustomerRequest devexplorerRequest;
    private CustomerEntity devexplorerEntity;
    private CustomerDto devexplorerDto;

    @MockBean
    private CustomerRepository repository;

    private CustomerService devexplorerService;


    @BeforeEach
    void setUp() {
        final CustomerConverter devexplorerConverter = new CustomerConverter();
        devexplorerRequest = generateCustomerRequest();
        devexplorerEntity = devexplorerConverter.toEntity(devexplorerRequest);
        devexplorerEntity.setId(CUSTOMER_ID);
        devexplorerDto = devexplorerConverter.toDto(devexplorerEntity);
        devexplorerListResponse = CustomerListResponse.builder().customers(Collections.singletonList(devexplorerDto)).build();
        devexplorerService = new CustomerServiceImpl(repository, devexplorerConverter);
    }

    @Test
    void getCustomer_success() {
        when(repository.findById(CUSTOMER_ID)).thenReturn(Optional.of(devexplorerEntity));
        final CustomerListResponse response = devexplorerService.getCustomer(CUSTOMER_ID);
        assertEquals(devexplorerListResponse, response);
    }

    @Test
    void getAllCustomers_success() {
        when(repository.findAll()).thenReturn(Collections.singletonList(devexplorerEntity));
        final CustomerListResponse response = devexplorerService.getAllCustomers();
        assertEquals(devexplorerListResponse, response);
    }

    @Test
    void getAllCustomers_notFound() {
        when(repository.findAll()).thenReturn(Collections.emptyList());
        final CustomerListResponse response = devexplorerService.getAllCustomers();
        assertTrue(CollectionUtils.isEmpty(response.getCustomers()));
    }

    @Test
    void createCustomer_success() {
        when(repository.save(any())).thenReturn(devexplorerEntity);
        final CustomerDto response = devexplorerService.createCustomer(devexplorerRequest);
        assertEquals(devexplorerDto, response);
    }

    @Test
    void updateCustomer_success() {
        when(repository.findById(CUSTOMER_ID)).thenReturn(Optional.of(devexplorerEntity));
        when(repository.save(any())).thenReturn(devexplorerEntity);
        final CustomerDto response = devexplorerService.updateCustomer(CUSTOMER_ID, devexplorerRequest);
        assertEquals(devexplorerDto, response);
    }

    @Test
    void updateCustomer_notFound() {
        when(repository.findById(CUSTOMER_ID)).thenReturn(Optional.empty());
        final CustomerDto response = devexplorerService.updateCustomer(CUSTOMER_ID, devexplorerRequest);
        assertNull(response);
    }

    @Test
    void deleteCustomer_success() {
        when(repository.existsById(CUSTOMER_ID)).thenReturn(true);
        doNothing().when(repository).deleteById(CUSTOMER_ID);
        final CustomerDeleteResponse response = devexplorerService.deleteCustomer(CUSTOMER_ID);
        assertNotNull(response);
        assertEquals(1, response.getDeletedCustomerCount());
    }

    @Test
    void deleteCustomer_noContent() {
        when(repository.existsById(CUSTOMER_ID)).thenReturn(false);
        final CustomerDeleteResponse response = devexplorerService.deleteCustomer(CUSTOMER_ID);
        assertEquals(0, response.getDeletedCustomerCount());
    }

    @Test
    void deleteAllCustomers_success() {
        when(repository.count()).thenReturn((long) 1);
        doNothing().when(repository).deleteAll();
        final CustomerDeleteResponse response = devexplorerService.deleteAllCustomers();
        assertNotNull(response);
        assertEquals(1, response.getDeletedCustomerCount());
    }

    @Test
    void deleteAllCustomers_noContent() {
        when(repository.count()).thenReturn((long) 0);
        final CustomerDeleteResponse response = devexplorerService.deleteAllCustomers();
        assertEquals(0, response.getDeletedCustomerCount());
    }

}
