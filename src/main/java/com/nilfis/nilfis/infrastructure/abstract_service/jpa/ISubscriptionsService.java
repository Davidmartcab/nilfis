package com.nilfis.nilfis.infrastructure.abstract_service.jpa;

import com.nilfis.nilfis.api.models.requests.jpa.SubscriptionsRequest;
import com.nilfis.nilfis.api.models.responses.jpa.SubscriptionsResponse;

import java.util.HashSet;
import java.util.UUID;

public interface ISubscriptionsService extends CrudService<SubscriptionsRequest, SubscriptionsResponse, UUID> {
    HashSet<SubscriptionsResponse> readByCustomerId(String uuid);

    void deleteByCustomerId(String uuid);

    HashSet<SubscriptionsResponse> readActiveByCustomer(String uuid);
    void deleteExpiredByCustomer(String uuid);

    void deleteAllExpired();

    void deleteAll();
}
