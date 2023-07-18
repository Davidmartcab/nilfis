package com.nilfis.nilfis.api.models.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SeriesByCustomerResponse {
    private CustomersResponse customer;
    private Set<SeriesResponse> series;
}