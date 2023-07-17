package com.nilfis.nilfis.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Entity(name = "subscriptions_types")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SubscriptionsTypesEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 20)
    private String name;

    private BigDecimal price;

    private String duration;

//    Estos mapeos sirven para declarar que a esta entidad pueden pertenecer muchas de otras
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            mappedBy = "type"
    )
    private Set<SubscriptionsEntity> subscriptions;

}
