package com.nilfis.nilfis.domain.repositories;

import com.nilfis.nilfis.domain.entities.SeriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;
import java.util.UUID;

public interface SeriesRepository extends JpaRepository<SeriesEntity, UUID> {
    @Query("SELECT s FROM series s WHERE LOWER(s.subscription_type_required) = LOWER(:type_name)")
    Set<SeriesEntity> findBySubscriptionName(String type_name);

    @Query("SELECT s FROM series s WHERE s.subscription_type_required IS NULL")
    Set<SeriesEntity> findByNoneSubscription();

    @Query("SELECT s FROM series s WHERE s.rate > :minRate")
    Set<SeriesEntity> findByRateGreaterThan(int minRate);

    @Query("SELECT s FROM series s WHERE s.rate < :maxRate")
    Set<SeriesEntity> findByRateLessThan(int maxRate);

    @Query("SELECT s FROM series s WHERE s.rate BETWEEN :minRate AND :maxRate")
    Set<SeriesEntity> findByRateBetween(int minRate, int maxRate);

    @Query("SELECT s FROM series s WHERE LOWER(s.title) LIKE LOWER(concat(:keyword, '%'))")
    Set<SeriesEntity> findByTitleStartingWith(String keyword);

    @Query("SELECT s FROM series s WHERE LOWER(s.director) LIKE LOWER(concat(:directorPrefix, '%'))")
    Set<SeriesEntity> findByDirectorStartingWith(String directorPrefix);

    @Query("SELECT s FROM series s WHERE s.year BETWEEN :startYear AND :endYear")
    Set<SeriesEntity> findByYearInRange(String startYear, String endYear);
}
