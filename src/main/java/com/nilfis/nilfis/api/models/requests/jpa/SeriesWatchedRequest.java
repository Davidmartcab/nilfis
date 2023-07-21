package com.nilfis.nilfis.api.models.requests.jpa;

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
public class SeriesWatchedRequest implements Serializable {
    private UUID customer_id;
    private UUID serie_id;
}
