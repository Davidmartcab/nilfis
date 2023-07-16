package com.nilfis.nilfis.domain.repositories;

import com.nilfis.nilfis.domain.entities.SubscriptionsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;
import java.util.UUID;

public interface SubscriptionsRepository extends CrudRepository<SubscriptionsEntity, UUID> {
    @Query("SELECT s FROM subscriptions s " +
            "WHERE s.date_start <= CURRENT_DATE " +
            "AND s.date_end >= CURRENT_DATE")
    Set<SubscriptionsEntity> findActiveSubscriptions();

    @Query("SELECT s FROM subscriptions s " +
            "JOIN s.customer c " +
            "WHERE c.id = :customerId " +
            "AND s.date_start <= CURRENT_DATE " +
            "AND s.date_end >= CURRENT_DATE")
    Set<SubscriptionsEntity> findActiveSubscriptionsByCustomerId(UUID customerId);

    @Query("SELECT s FROM subscriptions s " +
            "WHERE s.type.id = (SELECT st.id FROM subscriptions_types st " +
            "WHERE LOWER(st.name) = LOWER(:typeName))")
    Set<SubscriptionsEntity> findBySubscriptionType( String typeName);

}
