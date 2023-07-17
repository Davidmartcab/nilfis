package com.nilfis.nilfis.api.models.responses;

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
public class FilmsWatchedResponse implements Serializable {
    private UUID id;
    private LocalDate date;
    private CustomersResponse customer;
    private FilmsResponse film;
}
