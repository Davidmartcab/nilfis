package com.nilfis.nilfis.domain.repositories;

import com.nilfis.nilfis.domain.entities.SeriesEntity;
import com.nilfis.nilfis.domain.entities.SeriesWatchedEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;
import java.util.UUID;

public interface SeriesWatchedRepository extends CrudRepository<SeriesWatchedEntity, UUID> {

    @Query("SELECT f FROM series f " +
            "JOIN series_watched fw ON f.id = fw.serie.id " +
            "WHERE fw.customer.id = :customerId")
    Set<SeriesEntity> findSeriesWatchedByCustomer(UUID customerId);

    @Query("SELECT COUNT(sw) AS count " +
            "FROM series_watched sw " +
            "WHERE sw.serie.id = :serieId " +
            "GROUP BY sw.serie.id")
    Object[] countSeriesWatchOccurrencesBySeriesId(UUID serieId);
}
