package com.nilfis.nilfis.infrastructure.abstract_service;

import com.nilfis.nilfis.api.models.requests.SeriesRequest;
import com.nilfis.nilfis.api.models.responses.SeriesResponse;

import java.util.HashSet;
import java.util.UUID;

public interface ISeriesService extends CrudService<SeriesRequest, SeriesResponse, UUID> {
    HashSet<SeriesResponse> readBySubscriptionsType(String typeName);

    HashSet<SeriesResponse> readByRateGreater(int rate);

    HashSet<SeriesResponse> readByRateLower(int rate);

    HashSet<SeriesResponse> readByRateBetween(int minRate, int maxRate);

    HashSet<SeriesResponse> readByTitleStart(String titleStart);

    HashSet<SeriesResponse> readByDirectorStart(String directorStart);

    HashSet<SeriesResponse> readByYear(String startYear, String endYear);
}
