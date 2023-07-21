package com.nilfis.nilfis.domain.repositories.jpa;


import com.nilfis.nilfis.domain.entities.jpa.SubscriptionsTypesEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SubscriptionsTypesRepository extends CrudRepository<SubscriptionsTypesEntity, UUID> {

    @Query("SELECT s FROM subscriptions_types s WHERE LOWER(s.name) = LOWER(:type_name)")
    SubscriptionsTypesEntity findBySubscriptionName(String type_name);

}
