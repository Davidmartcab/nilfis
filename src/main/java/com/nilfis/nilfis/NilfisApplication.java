package com.nilfis.nilfis;

import com.nilfis.nilfis.domain.repositories.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
@AllArgsConstructor
@Slf4j
public class NilfisApplication implements CommandLineRunner {

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
    public void run(String... args) throws Exception {
        var var = this.subscriptionsRepository.findActiveSubscriptionsByCustomerId(UUID.fromString("48cde5cd-5d6c-4d97-8fad-be6beb297147"));
        System.out.println("----------------------------------------");
        var.forEach(v -> {
            System.out.println(v.getType());
        });
//        System.out.println(var);
        System.out.println("----------------------------------------");
    }
}