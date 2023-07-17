package com.nilfis.nilfis.domain.repositories;

import com.nilfis.nilfis.domain.entities.SeriesEntity;
import com.nilfis.nilfis.domain.entities.SeriesWatchedEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;
import java.util.UUID;

public interface SeriesWatchedRepository extends CrudRepository<SeriesWatchedEntity, UUID> {

    @Query("SELECT COUNT(sw) FROM series_watched sw WHERE sw.customer.id = :customerId")
    Long countSeriesWatchedByCustomerId(UUID customerId);

    @Query("SELECT f FROM series f " +
            "JOIN series_watched fw ON f.id = fw.serie.id " +
            "WHERE fw.customer.id = :customerId")
    Set<SeriesEntity> findSeriesWatchedByCustomer(UUID customerId);

    @Query("SELECT f FROM series f " +
            "JOIN series_watched fw ON f.id = fw.serie.id " +
            "WHERE fw.customer.id = :customerId " +
            "AND fw.serie.id = :serieId")
    Set<SeriesEntity> findSeriesWatchedByCustomerAndSerie(UUID customerId, UUID serieId);

    /*
      Por cada elemento del set hay que hacer lo siguiente:
      UUID filmId = (UUID) v[0];
        Long watchCount = (Long) v[1];
        System.out.println(filmId + " : " + watchCount);
    * */

    @Query("SELECT sw.serie.id, COUNT(sw) AS watchCount " +
            "FROM series_watched sw " +
            "WHERE sw.serie.id = :serieId " +
            "GROUP BY sw.serie.id")
    Set<Object[]> countSeriesWatchOccurrencesBySeriesId(UUID serieId);

    @Query("SELECT sw.serie.id, COUNT(sw) AS watchCount " +
            "FROM series_watched sw " +
            "GROUP BY sw.serie.id")
    Set<Object[]> countSeriesWatchOccurrences();
}
