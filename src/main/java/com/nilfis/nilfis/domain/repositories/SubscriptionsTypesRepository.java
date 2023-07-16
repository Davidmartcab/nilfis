package com.nilfis.nilfis.domain.repositories;

import com.nilfis.nilfis.domain.entities.SubscriptionsTypesEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SubscriptionsTypesRepository extends CrudRepository<SubscriptionsTypesEntity, UUID> {
}
