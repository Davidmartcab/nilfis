package com.nilfis.nilfis.infrastructure.abstract_service;

import com.nilfis.nilfis.api.models.requests.SeriesWatchedRequest;
import com.nilfis.nilfis.api.models.responses.CountSeriesResponse;
import com.nilfis.nilfis.api.models.responses.SeriesByCustomerResponse;
import com.nilfis.nilfis.api.models.responses.SeriesWatchedResponse;

import java.util.UUID;

public interface ISeriesWatchedService extends CrudService<SeriesWatchedRequest, SeriesWatchedResponse, UUID> {
    CountSeriesResponse readBySerie(UUID uuid);

    SeriesByCustomerResponse readByCustomer(UUID uuid);
}