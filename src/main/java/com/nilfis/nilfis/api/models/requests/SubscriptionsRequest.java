package com.nilfis.nilfis.api.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class SubscriptionsRequest implements Serializable {
    private UUID customer_id;
    @Size(min = 4, max = 20, message = "The subscription type must have 4 characters up to 20")
    @NotBlank(message = "Subscription type is required")
    private String subscriptionsType;
}
