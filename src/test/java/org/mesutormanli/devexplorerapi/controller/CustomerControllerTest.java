package org.mesutormanli.devexplorerapi.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mesutormanli.devexplorerapi.base.BaseControllerTest;
import org.mesutormanli.devexplorerapi.controller.CustomerController;
import org.mesutormanli.devexplorerapi.model.dto.CustomerDto;
import org.mesutormanli.devexplorerapi.model.request.CustomerRequest;
import org.mesutormanli.devexplorerapi.model.response.CustomerDeleteResponse;
import org.mesutormanli.devexplorerapi.model.response.CustomerListResponse;
import org.mesutormanli.devexplorerapi.service.CustomerService;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mesutormanli.devexplorerapi.builder.CustomerMockDataBuilder.*;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@WebMvcTest(value = CustomerController.class)
class CustomerControllerTest extends BaseControllerTest {

    private static final long CUSTOMER_ID = 1;
    private CustomerListResponse devexplorerListResponse;
    private CustomerRequest devexplorerRequest;
    private CustomerDto devexplorerDto;
    private CustomerDeleteResponse devexplorerDeleteResponse;

    @MockBean
    private CustomerService devexplorerService;

    @BeforeEach
    void setUp() {
        devexplorerListResponse = generateCustomerListResponse(CUSTOMER_ID);
        devexplorerRequest = generateCustomerRequest();
        devexplorerDto = generateCustomerDto(CUSTOMER_ID);
        devexplorerDeleteResponse = generateCustomerDeleteResponse();
        this.mockMvc = webAppContextSetup(wac)
                .apply(springSecurity(springSecurityFilterChain))
                .build();
    }

    //TODO: write authorization tests for non-get requests
    @Test
    void getCustomer() {
        when(devexplorerService.getCustomer(CUSTOMER_ID)).thenReturn(devexplorerListResponse);
        try {
            mockMvc.perform(get("/devexplorer/{id}", CUSTOMER_ID)
                            .with(httpBasic("user", "password"))
                            .with(csrf()))
                    .andDo(print())
                    .andExpect(status().isOk());
        } catch (Exception e) {
            fail(e);
        }

        verify(devexplorerService, times(1)).getCustomer(CUSTOMER_ID);
        verifyNoMoreInteractions(devexplorerService);
    }

    @Test
    void getCustomer_notFound() {
        when(devexplorerService.getCustomer(CUSTOMER_ID)).thenReturn(new CustomerListResponse());
        try {
            mockMvc.perform(get("/devexplorer/{id}", CUSTOMER_ID)
                            .with(httpBasic("user", "password"))
                            .with(csrf()))
                    .andDo(print())
                    .andExpect(status().isNotFound());
        } catch (Exception e) {
            fail(e);
        }

        verify(devexplorerService, times(1)).getCustomer(CUSTOMER_ID);
        verifyNoMoreInteractions(devexplorerService);
    }

    @Test
    void getAllCustomers() {
        when(devexplorerService.getAllCustomers()).thenReturn(devexplorerListResponse);
        try {
            mockMvc.perform(get("/devexplorers")
                            .with(httpBasic("user", "password"))
                            .with(csrf()))
                    .andDo(print())
                    .andExpect(status().isOk());
        } catch (Exception e) {
            fail(e);
        }

        verify(devexplorerService, times(1)).getAllCustomers();
        verifyNoMoreInteractions(devexplorerService);
    }

    @Test
    void createCustomer() {
        when(devexplorerService.createCustomer(devexplorerRequest)).thenReturn(devexplorerDto);

        try {
            mockMvc.perform(post("/devexplorer").with(httpBasic("admin", "admin"))
                            .with(csrf())
                            .contentType(contentType)
                            .content(json(devexplorerRequest))
                    )
                    .andDo(print())
                    .andExpect(status().isCreated());
        } catch (Exception e) {
            fail(e);
        }

        verify(devexplorerService, times(1)).createCustomer(devexplorerRequest);
        verifyNoMoreInteractions(devexplorerService);
    }

    @Test
    void updateCustomer() {
        when(devexplorerService.updateCustomer(CUSTOMER_ID, devexplorerRequest)).thenReturn(devexplorerDto);
        try {
            mockMvc.perform(put("/devexplorer/{id}", CUSTOMER_ID)
                            .with(httpBasic("admin", "admin"))
                            .with(csrf())
                            .contentType(contentType)
                            .content(json(devexplorerRequest))
                    )
                    .andDo(print())
                    .andExpect(status().isOk());
        } catch (Exception e) {
            fail(e);
        }

        verify(devexplorerService, times(1)).updateCustomer(CUSTOMER_ID, devexplorerRequest);
        verifyNoMoreInteractions(devexplorerService);
    }

    @Test
    void updateCustomer_notFound() {
        when(devexplorerService.updateCustomer(CUSTOMER_ID, devexplorerRequest)).thenReturn(null);
        try {
            mockMvc.perform(put("/devexplorer/{id}", CUSTOMER_ID)
                            .with(httpBasic("admin", "admin"))
                            .with(csrf())
                            .contentType(contentType)
                            .content(json(devexplorerRequest))
                    )
                    .andDo(print())
                    .andExpect(status().isNotFound());
        } catch (Exception e) {
            fail(e);
        }

        verify(devexplorerService, times(1)).updateCustomer(CUSTOMER_ID, devexplorerRequest);
        verifyNoMoreInteractions(devexplorerService);
    }

    @Test
    void deleteCustomer() {
        when(devexplorerService.deleteCustomer(CUSTOMER_ID)).thenReturn(devexplorerDeleteResponse);
        try {
            mockMvc.perform(delete("/devexplorer/{id}", CUSTOMER_ID)
                            .with(httpBasic("admin", "admin"))
                            .with(csrf()))
                    .andDo(print())
                    .andExpect(status().isOk());
        } catch (Exception e) {
            fail(e);
        }

        verify(devexplorerService, times(1)).deleteCustomer(CUSTOMER_ID);
        verifyNoMoreInteractions(devexplorerService);
    }

    @Test
    void deleteCustomer_noContent() {
        when(devexplorerService.deleteCustomer(CUSTOMER_ID)).thenReturn(CustomerDeleteResponse.builder().deletedCustomerCount(0L).build());
        try {
            mockMvc.perform(delete("/devexplorer/{id}", CUSTOMER_ID)
                            .with(httpBasic("admin", "admin"))
                            .with(csrf()))
                    .andDo(print())
                    .andExpect(status().isNoContent());
        } catch (Exception e) {
            fail(e);
        }

        verify(devexplorerService, times(1)).deleteCustomer(CUSTOMER_ID);
        verifyNoMoreInteractions(devexplorerService);
    }

    @Test
    void deleteAllCustomers() {
        when(devexplorerService.deleteAllCustomers()).thenReturn(devexplorerDeleteResponse);
        try {
            mockMvc.perform(delete("/devexplorers")
                            .with(httpBasic("admin", "admin"))
                            .with(csrf()))
                    .andDo(print())
                    .andExpect(status().isOk());
        } catch (Exception e) {
            fail(e);
        }

        verify(devexplorerService, times(1)).deleteAllCustomers();
        verifyNoMoreInteractions(devexplorerService);
    }

    @Test
    void deleteAllCustomers_noContent() {
        when(devexplorerService.deleteAllCustomers()).thenReturn(CustomerDeleteResponse.builder().deletedCustomerCount(0L).build());
        try {
            mockMvc.perform(delete("/devexplorers")
                            .with(httpBasic("admin", "admin"))
                            .with(csrf()))
                    .andDo(print())
                    .andExpect(status().isNoContent());
        } catch (Exception e) {
            fail(e);
        }

        verify(devexplorerService, times(1)).deleteAllCustomers();
        verifyNoMoreInteractions(devexplorerService);
    }
}




