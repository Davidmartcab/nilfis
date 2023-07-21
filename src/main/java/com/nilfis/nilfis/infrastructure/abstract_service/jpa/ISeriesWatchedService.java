package com.nilfis.nilfis.infrastructure.abstract_service.jpa;

import com.nilfis.nilfis.api.models.requests.jpa.SeriesWatchedRequest;
import com.nilfis.nilfis.api.models.responses.jpa.CountSeriesResponse;
import com.nilfis.nilfis.api.models.responses.jpa.SeriesByCustomerResponse;
import com.nilfis.nilfis.api.models.responses.jpa.SeriesWatchedResponse;

import java.util.UUID;

public interface ISeriesWatchedService extends CrudService<SeriesWatchedRequest, SeriesWatchedResponse, UUID> {
    CountSeriesResponse readBySerie(UUID uuid);

    SeriesByCustomerResponse readByCustomer(UUID uuid);
}