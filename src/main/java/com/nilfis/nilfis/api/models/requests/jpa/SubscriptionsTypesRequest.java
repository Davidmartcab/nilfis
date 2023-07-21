package com.nilfis.nilfis.api.models.requests.jpa;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @Size(min = 4, max = 20, message = "The name must have 4 characters up to 20")
    @NotBlank(message = "Name type is required")
    private String name;

    @Min(0)
    private BigDecimal price;

    private String duration;
}
