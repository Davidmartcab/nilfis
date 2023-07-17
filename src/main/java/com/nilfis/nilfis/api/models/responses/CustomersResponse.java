package com.nilfis.nilfis.api.models.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CustomersResponse implements Serializable {
    private UUID id;
    private String name;
    private String email;
    private String phone;
    private String country;
    private boolean verified;
}
