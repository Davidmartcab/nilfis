package com.nilfis.nilfis.domain.repositories;

import com.nilfis.nilfis.domain.entities.CustomersEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CustomersRepository extends CrudRepository<CustomersEntity, UUID> {
}
