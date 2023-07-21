package com.nilfis.nilfis.domain.repositories.jpa;

import com.nilfis.nilfis.domain.entities.jpa.FilmsEntity;
import com.nilfis.nilfis.domain.entities.jpa.FilmsWatchedEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;
import java.util.UUID;

public interface FilmsWatchedRepository extends CrudRepository<FilmsWatchedEntity, UUID> {

    @Query("SELECT f FROM films f " +
            "JOIN films_watched fw ON f.id = fw.film.id " +
            "WHERE fw.customer.id = :customerId")
    Set<FilmsEntity> findFilmsWatchedByCustomer(UUID customerId);

    @Query("SELECT COUNT(fw) AS count " +
            "FROM films_watched fw " +
            "WHERE fw.film.id = :filmId " +
            "GROUP BY fw.film.id")
    Object[] countFilmWatchOccurrencesByFilmId(UUID filmId);
}
