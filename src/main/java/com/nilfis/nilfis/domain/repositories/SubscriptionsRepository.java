package com.nilfis.nilfis.domain.repositories;

import com.nilfis.nilfis.domain.entities.SubscriptionsEntity;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.Set;
import java.util.UUID;

public interface SubscriptionsRepository extends CrudRepository<SubscriptionsEntity, UUID> {

    @Query("SELECT s FROM subscriptions s " +
            "WHERE s.date_start <= CURRENT_DATE " +
            "AND s.date_end >= CURRENT_DATE")
    Set<SubscriptionsEntity> findActiveSubscriptions();

    @Query("SELECT s FROM subscriptions s " +
            "JOIN s.customer c " +
            "WHERE c.id = :customerId " +
            "AND s.date_start <= CURRENT_DATE " +
            "AND s.date_end >= CURRENT_DATE")
    Set<SubscriptionsEntity> findActiveSubscriptionsByCustomerId(UUID customerId);

    @Query("SELECT s FROM subscriptions s " +
            "JOIN s.customer c " +
            "WHERE c.id = :customerId " +
            "AND s.date_end >= CURRENT_DATE")
    Set<SubscriptionsEntity> findAllSubscriptionsByCustomerId(UUID customerId);

    @Query("SELECT s FROM subscriptions s " +
            "WHERE s.type.id = (SELECT st.id FROM subscriptions_types st " +
            "WHERE LOWER(st.name) = LOWER(:typeName))")
    Set<SubscriptionsEntity> findBySubscriptionType(String typeName);

    @Transactional
    @Modifying
    @Query("DELETE FROM subscriptions s WHERE s.id = :subscriptionId")
    void deleteSubscriptionById(UUID subscriptionId);

    @Transactional
    @Modifying
    @Query("DELETE FROM subscriptions s WHERE s.customer.id = :customerId")
    void deleteAllSubscriptionsByCustomerId(UUID customerId);

    @Transactional
    @Modifying
    @Query("DELETE FROM subscriptions s WHERE s.customer.id = :customerId AND s.date_end < CURRENT_DATE")
    void deleteExpiredSubscriptionsByCustomerId(UUID customerId);

    @Transactional
    @Modifying
    @Query("DELETE FROM subscriptions s WHERE s.date_end < CURRENT_DATE")
    void deleteExpiredSubscriptions();

    @Transactional
    public default void insertNewSubscription(EntityManager entityManager, UUID customerId, String subscriptionTypeName) {
        // Obtener el ID del tipo de suscripción que coincide con el nombre proporcionado
        String getTypeQuery = "SELECT st.id FROM subscriptions_types st WHERE st.name = :subscriptionTypeName";
        UUID typeId = entityManager.createQuery(getTypeQuery, UUID.class)
                .setParameter("subscriptionTypeName", subscriptionTypeName)
                .getSingleResult();

        // Calcular las fechas de inicio y fin de la suscripción
        LocalDate currentDate = LocalDate.now();
        String getDurationQuery = "SELECT st.duration FROM subscriptions_types st WHERE st.id = :typeId";
        String duration = entityManager.createQuery(getDurationQuery, String.class)
                .setParameter("typeId", typeId)
                .getSingleResult();

        // Parsear la cadena de duración para obtener el número
        int days = Integer.parseInt(duration.split(" ")[0]);
        LocalDate endDate = currentDate.plus(Period.ofDays(days));

        // Insertar la nueva suscripción
        String insertQuery = "INSERT INTO subscriptions (customer_id, date_start, date_end, type_id) " +
                "VALUES (:customerId, :dateStart, :dateEnd, :typeId)";
        entityManager.createNativeQuery(insertQuery)
                .setParameter("customerId", customerId)
                .setParameter("dateStart", currentDate)
                .setParameter("dateEnd", endDate)
                .setParameter("typeId", typeId)
                .executeUpdate();
    }

}
