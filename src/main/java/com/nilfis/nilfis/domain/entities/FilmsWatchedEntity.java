package com.nilfis.nilfis.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "films_watched")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FilmsWatchedEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDate date;

//    Estos mapeos indican que esta entidad solo puede pertenecer a otra entidad en específico
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomersEntity customer;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private FilmsEntity film;
}
