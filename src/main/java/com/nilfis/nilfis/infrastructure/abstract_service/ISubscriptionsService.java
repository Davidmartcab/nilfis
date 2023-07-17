package com.nilfis.nilfis.infrastructure.abstract_service;

import com.nilfis.nilfis.api.models.requests.SubscriptionsRequest;
import com.nilfis.nilfis.api.models.responses.SubscriptionsResponse;

import java.util.HashSet;
import java.util.UUID;

public interface ISubscriptionsService extends CrudService<SubscriptionsRequest, SubscriptionsResponse, UUID> {
    HashSet<SubscriptionsResponse> readByCustomerId(UUID uuid);
    void deleteByCustomerId(UUID uuid);

    HashSet<SubscriptionsResponse> readActiveByCustomer(UUID uuid);
    void deleteExpiredByCustomer(UUID uuid);

    void deleteAllExpired();

    void deleteAll();
}
