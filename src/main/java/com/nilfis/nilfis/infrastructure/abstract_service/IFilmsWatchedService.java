package com.nilfis.nilfis.infrastructure.abstract_service;

import com.nilfis.nilfis.api.models.requests.FilmsWatchedRequest;
import com.nilfis.nilfis.api.models.responses.CountFilmsResponse;
import com.nilfis.nilfis.api.models.responses.FilmsByCustomerResponse;
import com.nilfis.nilfis.api.models.responses.FilmsWatchedResponse;

import java.util.UUID;

public interface IFilmsWatchedService extends CrudService<FilmsWatchedRequest, FilmsWatchedResponse, UUID> {
    CountFilmsResponse readByFilm(UUID uuid);

    FilmsByCustomerResponse readByCustomer(UUID uuid);
}
