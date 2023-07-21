package com.nilfis.nilfis.infrastructure.abstract_service.jpa;

import com.nilfis.nilfis.api.models.requests.jpa.FilmsRequest;
import com.nilfis.nilfis.api.models.responses.jpa.FilmsResponse;

import java.util.UUID;

public interface IFilmsService extends CrudService<FilmsRequest, FilmsResponse, UUID> {
}
