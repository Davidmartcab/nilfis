package com.nilfis.nilfis.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity(name = "series")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SeriesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 50)
    private String title;

    @Column(length = 50)
    private String director;

    @Column(length = 4)
    private String year;

    private String url;

    private Integer rate;

    private Integer chapters;

    @Column(length = 20)
    private String subscription_type_required;

//    Estos mapeos sirven para declarar que a esta entidad pueden pertenecer muchas de otras
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true,
            mappedBy = "serie"
    )
    private Set<SeriesWatchedEntity> seriesWatched;
}