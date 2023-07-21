package com.nilfis.nilfis.api.models.responses.jpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CountSeriesResponse {
    private SeriesResponse serie;
    private long count;
}
