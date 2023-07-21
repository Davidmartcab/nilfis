package com.nilfis.nilfis.domain.repositories.mongo;

import com.nilfis.nilfis.domain.entities.documents.AppUserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AppUserRepository extends MongoRepository<AppUserDocument, String> {
    Optional<AppUserDocument> findByEmail(String email);
}
