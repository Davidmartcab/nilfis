package com.nilfis.nilfis.infrastructure.abstract_service.jpa;

import com.nilfis.nilfis.api.models.requests.jpa.FilmsWatchedRequest;
import com.nilfis.nilfis.api.models.responses.jpa.CountFilmsResponse;
import com.nilfis.nilfis.api.models.responses.jpa.FilmsByCustomerResponse;
import com.nilfis.nilfis.api.models.responses.jpa.FilmsWatchedResponse;

import java.util.UUID;

public interface IFilmsWatchedService extends CrudService<FilmsWatchedRequest, FilmsWatchedResponse, UUID> {
    CountFilmsResponse readByFilm(UUID uuid);

    FilmsByCustomerResponse readByCustomer(UUID uuid);
}
