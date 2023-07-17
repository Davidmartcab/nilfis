package com.nilfis.nilfis.api.models.requests;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SubscriptionsTypesRequest implements Serializable {
    private String name;
    private BigDecimal price;
    private String duration;
}
