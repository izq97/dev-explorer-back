package org.mesutormanli.devexplorerapi.service;

import org.mesutormanli.devexplorerapi.model.dto.CustomerDto;
import org.mesutormanli.devexplorerapi.model.request.CustomerRequest;
import org.mesutormanli.devexplorerapi.model.response.CustomerDeleteResponse;
import org.mesutormanli.devexplorerapi.model.response.CustomerListResponse;

public interface CustomerService {
    CustomerListResponse getCustomer(Long id);

    CustomerListResponse getAllCustomers();

    CustomerDto createCustomer(CustomerRequest request);

    CustomerDto updateCustomer(Long id, CustomerRequest request);

    CustomerDeleteResponse deleteCustomer(Long id);

    CustomerDeleteResponse deleteAllCustomers();

}
