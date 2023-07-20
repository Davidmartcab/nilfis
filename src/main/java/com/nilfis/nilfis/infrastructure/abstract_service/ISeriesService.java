package com.nilfis.nilfis.infrastructure.abstract_service;

import com.nilfis.nilfis.api.models.requests.SeriesRequest;
import com.nilfis.nilfis.api.models.responses.SeriesResponse;

import java.util.UUID;

public interface ISeriesService extends CrudService<SeriesRequest, SeriesResponse, UUID> {
}
