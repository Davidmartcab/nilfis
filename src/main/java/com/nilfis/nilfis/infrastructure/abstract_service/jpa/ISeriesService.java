package com.nilfis.nilfis.infrastructure.abstract_service.jpa;

import com.nilfis.nilfis.api.models.requests.jpa.SeriesRequest;
import com.nilfis.nilfis.api.models.responses.jpa.SeriesResponse;

import java.util.UUID;

public interface ISeriesService extends CrudService<SeriesRequest, SeriesResponse, UUID> {
}
