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
public class SubscriptionsResponse implements Serializable {
    private UUID id;
    private LocalDate date_start;
    private LocalDate date_end;
    private CustomersResponse customer;
    private SubscriptionsTypesResponse type;
}
