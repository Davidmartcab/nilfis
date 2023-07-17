package com.nilfis.nilfis.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "subscriptions")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SubscriptionsEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDate date_start;

    private LocalDate date_end;

//    Estos mapeos indican que esta entidad solo puede pertenecer a otra entidad en específico
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomersEntity customer;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private SubscriptionsTypesEntity type;
}
