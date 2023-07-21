package com.nilfis.nilfis.api.models.responses.jpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SubscriptionsTypesResponse implements Serializable {
    private UUID id;
    private String name;
    private BigDecimal price;
    private String duration;
}
