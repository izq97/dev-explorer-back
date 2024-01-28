package org.mesutormanli.devexplorerapi.model.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class CustomerDeleteResponse implements Serializable {
    private Long deletedCustomerCount;
}
