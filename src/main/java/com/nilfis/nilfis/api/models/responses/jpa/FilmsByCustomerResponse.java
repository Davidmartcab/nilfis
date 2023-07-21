package com.nilfis.nilfis.api.models.responses.jpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FilmsByCustomerResponse {
    private CustomersResponse customer;
    private Set<FilmsResponse> films;
}
