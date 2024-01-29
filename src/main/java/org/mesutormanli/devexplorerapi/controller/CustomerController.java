package org.mesutormanli.devexplorerapi.controller;

import java.util.List;

import org.mesutormanli.devexplorerapi.model.dto.CustomerDto;
import org.mesutormanli.devexplorerapi.model.entity.DevExplorer;
import org.mesutormanli.devexplorerapi.model.entity.Tecnologia;
import org.mesutormanli.devexplorerapi.model.request.CustomerRequest;
import org.mesutormanli.devexplorerapi.model.response.CustomerListResponse;
import org.mesutormanli.devexplorerapi.service.CustomerService;
import org.mesutormanli.devexplorerapi.service.DevExplorerService;
import org.mesutormanli.devexplorerapi.service.TecnologiaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    private final CustomerService customerService;
    private final DevExplorerService devExplorerService;
    private final TecnologiaService tecnologiaService;

    public CustomerController(CustomerService customerService, DevExplorerService devExplorerService,
            TecnologiaService tecnologiaService) {
        this.customerService = customerService;
        this.devExplorerService = devExplorerService;
        this.tecnologiaService = tecnologiaService;
    }

    @GetMapping("/devexplorer/{id}")
    public ResponseEntity<CustomerListResponse> getCustomer(@PathVariable("id") Long id) {
        CustomerListResponse response = customerService.getCustomer(id);

        if (CollectionUtils.isEmpty(response.getCustomers())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

    }

    @GetMapping("/devexplorers")
    public ResponseEntity<CustomerListResponse> getAllCustomers() {
        CustomerListResponse response = customerService.getAllCustomers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/devexplorer")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerRequest request) {
        return new ResponseEntity<>(customerService.createCustomer(request), HttpStatus.CREATED);
    }

    @GetMapping("/informacion/{id}")
    public ResponseEntity<DevExplorer> getDevExplorer(@PathVariable("id") Long id) {
        DevExplorer response = devExplorerService.getDevExplorer(id);

        if (response == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping("/tecnologias")
    public ResponseEntity<List<Tecnologia>> getAllTecnologia() {
        List<Tecnologia> response = tecnologiaService.getAllTecnologias();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
