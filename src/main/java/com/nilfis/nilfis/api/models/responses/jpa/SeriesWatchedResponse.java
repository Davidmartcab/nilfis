package com.nilfis.nilfis.api.models.responses.jpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SeriesWatchedResponse implements Serializable {
    private UUID id;
    private LocalDate date;
    private CustomersResponse customer;
    private SeriesResponse serie;
}
