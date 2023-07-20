package com.nilfis.nilfis.infrastructure.abstract_service;

import com.nilfis.nilfis.api.models.requests.FilmsRequest;
import com.nilfis.nilfis.api.models.responses.FilmsResponse;

import java.util.UUID;

public interface IFilmsService extends CrudService<FilmsRequest, FilmsResponse, UUID> {
}
