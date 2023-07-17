package com.nilfis.nilfis.infrastructure.abstract_service;

import com.nilfis.nilfis.api.models.requests.SubscriptionsTypesRequest;
import com.nilfis.nilfis.api.models.responses.SubscriptionsTypesResponse;

import java.util.HashSet;
import java.util.UUID;

public interface ISubscriptionsTypesService extends CrudService<SubscriptionsTypesRequest, SubscriptionsTypesResponse, UUID>{
}
