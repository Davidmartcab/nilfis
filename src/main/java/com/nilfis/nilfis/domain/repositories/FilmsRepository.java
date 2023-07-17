package com.nilfis.nilfis.domain.repositories;

import com.nilfis.nilfis.domain.entities.FilmsEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;
import java.util.UUID;

public interface FilmsRepository extends JpaRepository<FilmsEntity, UUID> {

    @Query("SELECT f FROM films f WHERE LOWER(f.subscription_type_required) = LOWER(:type_name)")
    Set<FilmsEntity> findBySubscriptionName(String type_name);

    @Query("SELECT f FROM films f WHERE f.subscription_type_required IS NULL")
    Set<FilmsEntity> findByNoneSubscription();


    @Query("SELECT f FROM films f WHERE f.rate > :minRate")
    Set<FilmsEntity> findByRateGreaterThan(int minRate);

    @Query("SELECT f FROM films f WHERE f.rate < :maxRate")
    Set<FilmsEntity> findByRateLessThan(int maxRate);

    @Query("SELECT f FROM films f WHERE f.rate BETWEEN :minRate AND :maxRate")
    Set<FilmsEntity> findByRateBetween(int minRate, int maxRate);


    @Query("SELECT f FROM films f WHERE LOWER(f.title) LIKE LOWER(concat(:keyword, '%'))")
    Set<FilmsEntity> findByTitleStartingWith(String keyword);

    @Query("SELECT f FROM films f WHERE LOWER(f.director) LIKE LOWER(concat(:directorPrefix, '%'))")
    Set<FilmsEntity> findByDirectorStartingWith(String directorPrefix);


    @Query("SELECT f FROM films f WHERE f.year BETWEEN :startYear AND :endYear")
    Set<FilmsEntity> findByYearInRange(String startYear, String endYear);
}
