package com.nilfis.nilfis.domain.repositories;

import com.nilfis.nilfis.domain.entities.FilmsWatchedEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;
import java.util.UUID;

public interface FilmsWatchedRepository extends CrudRepository<FilmsWatchedEntity, UUID> {

    @Query("SELECT COUNT(fw) FROM films_watched fw WHERE fw.customer.id = :customerId")
    Long countFilmsWatchedByCustomerId(UUID customerId);

    /*
      Por cada elemento del set hay que hacer lo siguiente:
      UUID filmId = (UUID) v[0];
        Long watchCount = (Long) v[1];
        System.out.println(filmId + " : " + watchCount);
    * */

    @Query("SELECT fw.film.id, COUNT(fw) AS watchCount " +
            "FROM films_watched fw " +
            "WHERE fw.film.id = :filmId " +
            "GROUP BY fw.film.id")
    Set<Object[]> countFilmWatchOccurrencesByFilmId(UUID filmId);

    @Query("SELECT fw.film.id, COUNT(fw) AS watchCount " +
            "FROM films_watched fw " +
            "GROUP BY fw.film.id")
    Set<Object[]> countFilmWatchOccurrences();
}
