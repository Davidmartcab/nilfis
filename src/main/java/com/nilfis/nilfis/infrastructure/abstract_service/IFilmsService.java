package com.nilfis.nilfis.infrastructure.abstract_service;

import com.nilfis.nilfis.api.models.requests.FilmsRequest;
import com.nilfis.nilfis.api.models.responses.FilmsResponse;

import java.util.HashSet;
import java.util.UUID;

public interface IFilmsService extends CrudService<FilmsRequest, FilmsResponse, UUID> {
    HashSet<FilmsResponse> readBySubscriptionsType(String typeName);

    HashSet<FilmsResponse> readByRateGreater(int rate);

    HashSet<FilmsResponse> readByRateLower(int rate);

    HashSet<FilmsResponse> readByRateBetween(int minRate, int maxRate);

    HashSet<FilmsResponse> readByTitleStart(String titleStart);

    HashSet<FilmsResponse> readByDirectorStart(String directorStart);

    HashSet<FilmsResponse> readByYear(String startYear, String endYear);
}
