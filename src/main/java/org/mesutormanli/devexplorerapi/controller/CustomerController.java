package org.mesutormanli.devexplorerapi.controller;

import org.mesutormanli.devexplorerapi.model.dto.CustomerDto;
import org.mesutormanli.devexplorerapi.model.request.CustomerRequest;
import org.mesutormanli.devexplorerapi.model.response.CustomerDeleteResponse;
import org.mesutormanli.devexplorerapi.model.response.CustomerListResponse;
import org.mesutormanli.devexplorerapi.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    private final CustomerService devexplorerService;

    public CustomerController(CustomerService devexplorerService) {
        this.devexplorerService = devexplorerService;
    }

    @GetMapping("/devexplorer/{id}")
    public ResponseEntity<CustomerListResponse> getCustomer(@PathVariable("id") Long id) {
        CustomerListResponse response = devexplorerService.getCustomer(id);

        if (CollectionUtils.isEmpty(response.getCustomers())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

    }

    @GetMapping("/devexplorers")
    public ResponseEntity<CustomerListResponse> getAllCustomers() {
        CustomerListResponse response = devexplorerService.getAllCustomers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/devexplorer")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerRequest request) {
        return new ResponseEntity<>(devexplorerService.createCustomer(request), HttpStatus.CREATED);
    }

    @PutMapping("/devexplorer/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable("id") Long id, @RequestBody CustomerRequest request) {
        CustomerDto devexplorerDto = devexplorerService.updateCustomer(id, request);
        if (null == devexplorerDto) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(devexplorerDto, HttpStatus.OK);
        }
    }

    @DeleteMapping("/devexplorer/{id}")
    public ResponseEntity<CustomerDeleteResponse> deleteCustomer(@PathVariable("id") Long id) {
        CustomerDeleteResponse response = devexplorerService.deleteCustomer(id);
        if (0L == response.getDeletedCustomerCount()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @DeleteMapping("/devexplorers")
    public ResponseEntity<CustomerDeleteResponse> deleteAllCustomers() {
        CustomerDeleteResponse response = devexplorerService.deleteAllCustomers();
        if (0L == response.getDeletedCustomerCount()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

}
