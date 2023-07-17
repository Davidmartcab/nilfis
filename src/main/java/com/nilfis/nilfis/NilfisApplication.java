package com.nilfis.nilfis;

import com.nilfis.nilfis.domain.entities.FilmsEntity;
import com.nilfis.nilfis.domain.repositories.*;
import com.nilfis.nilfis.util.DurationInterval;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
@Slf4j
public class NilfisApplication implements CommandLineRunner {

    private final EntityManager entityManager;

    private final FilmsRepository filmsRepository;

    private final SeriesRepository seriesRepository;

    private final CustomersRepository customersRepository;

    private final FilmsWatchedRepository filmsWatchedRepository;

    private final SeriesWatchedRepository seriesWatchedRepository;

    private final SubscriptionsRepository subscriptionsRepository;

    private final SubscriptionsTypesRepository subscriptionsTypesRepository;

    public static void main(String[] args) {
        SpringApplication.run(NilfisApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        DurationInterval dur = new DurationInterval();
        dur.setHours(2);
        dur.setMinutes(30);
        String duration = dur.getInterval();
        FilmsEntity film2 = FilmsEntity.builder()
                .title("Film Title 2")
                .director("Director 2")
                .year("2021")
                .url("https://example.com/film2")
                .rate(4)
                .duration(duration)
                .subscription_type_required("Basic")
                .build();
        this.filmsRepository.save(film2);
    }
}