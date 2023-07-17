package com.nilfis.nilfis.domain.repositories;

import com.nilfis.nilfis.domain.entities.CustomersEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;
import java.util.UUID;

public interface CustomersRepository extends CrudRepository<CustomersEntity, UUID> {

    @Query("SELECT c FROM customers c WHERE LOWER(c.email) = LOWER(:email)")
    CustomersEntity findByEmail(String email);
}
