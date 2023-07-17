package com.nilfis.nilfis.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity(name = "customers")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CustomersEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 50)
    private String name;

    @Column(length = 50)
    private String email;

    @Column(length = 12)
    private String phone;

    @Column(length = 20)
    private String country;

    private boolean verified;

//    Estos mapeos sirven para declarar que a esta entidad pueden pertenecer muchas de otras
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            mappedBy = "customer"
    )
    private Set<FilmsWatchedEntity> filmsWatched;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            mappedBy = "customer"
    )
    private Set<SeriesWatchedEntity> seriesWatched;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            mappedBy = "customer"
    )
    private Set<SubscriptionsEntity> subscriptions;

}
