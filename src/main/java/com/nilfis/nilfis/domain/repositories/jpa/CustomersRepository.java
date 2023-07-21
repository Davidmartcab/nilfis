package com.nilfis.nilfis.domain.repositories.jpa;

import com.nilfis.nilfis.domain.entities.jpa.CustomersEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.HashSet;
import java.util.UUID;

public interface CustomersRepository extends CrudRepository<CustomersEntity, UUID> {

    @Query("SELECT c FROM customers c WHERE LOWER(c.user_id) = LOWER(:user_id)")
    HashSet<CustomersEntity> findByUserId(String user_id);
}
