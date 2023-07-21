package com.nilfis.nilfis.infrastructure.abstract_service.jpa;

import com.nilfis.nilfis.api.models.requests.jpa.SubscriptionsTypesRequest;
import com.nilfis.nilfis.api.models.responses.jpa.SubscriptionsTypesResponse;

import java.util.UUID;

public interface ISubscriptionsTypesService extends CrudService<SubscriptionsTypesRequest, SubscriptionsTypesResponse, UUID>{
}
