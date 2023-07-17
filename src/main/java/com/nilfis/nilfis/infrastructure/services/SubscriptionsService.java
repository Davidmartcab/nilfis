package com.nilfis.nilfis.infrastructure.services;

import com.nilfis.nilfis.api.models.requests.SubscriptionsRequest;
import com.nilfis.nilfis.api.models.responses.CustomersResponse;
import com.nilfis.nilfis.api.models.responses.SeriesResponse;
import com.nilfis.nilfis.api.models.responses.SubscriptionsResponse;
import com.nilfis.nilfis.api.models.responses.SubscriptionsTypesResponse;
import com.nilfis.nilfis.domain.entities.SeriesEntity;
import com.nilfis.nilfis.domain.entities.SubscriptionsEntity;
import com.nilfis.nilfis.domain.entities.SubscriptionsTypesEntity;
import com.nilfis.nilfis.domain.repositories.CustomersRepository;
import com.nilfis.nilfis.domain.repositories.SubscriptionsRepository;
import com.nilfis.nilfis.domain.repositories.SubscriptionsTypesRepository;
import com.nilfis.nilfis.infrastructure.abstract_service.ISubscriptionsService;
import com.nilfis.nilfis.util.DurationInterval;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.UUID;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class SubscriptionsService implements ISubscriptionsService {

    private final SubscriptionsRepository subscriptionsRepository;
    private final SubscriptionsTypesRepository subscriptionsTypesRepository;
    private final CustomersRepository customersRepository;

    @Override
    public SubscriptionsResponse create(SubscriptionsRequest request) {
        var customer = this.customersRepository.findById(request.getCustomer_id()).orElseThrow();

        var typeFound = new SubscriptionsTypesEntity();
        try {
            typeFound = this.subscriptionsTypesRepository.findBySubscriptionName(request.getSubscriptionsType());
        } catch (Exception e) {
            log.info("Error: customer not found");
            return null;
        }

        DurationInterval transformer = new DurationInterval();
        transformer.setInterval(typeFound.getDuration());

        var subscriptionToPersist = SubscriptionsEntity.builder()
                .date_start(LocalDate.now())
                .date_end(LocalDate.now().plusDays(transformer.getDays()))
                .customer(customer)
                .type(typeFound)
                .build();
        var subscriptionPersisted = this.subscriptionsRepository.save(subscriptionToPersist);

        return this.entityToResponse(subscriptionPersisted);
    }

    @Override
    public SubscriptionsResponse read(UUID uuid) {
        var subscriptionsFromDB = this.subscriptionsRepository.findById(uuid).orElseThrow();
        return this.entityToResponse(subscriptionsFromDB);
    }

    @Override
    public HashSet<SubscriptionsResponse> read() {
        var subscriptionsFromDB = this.subscriptionsRepository.findAll();
        var subscriptionsForResponse = new HashSet<SubscriptionsResponse>();
        for(SubscriptionsEntity s : subscriptionsFromDB) {
            subscriptionsForResponse.add(this.entityToResponse(s));
        }

        return subscriptionsForResponse;
    }

    @Override
    public void delete(UUID uuid) {
        var subscriptionToDelete = this.subscriptionsRepository.findById(uuid).orElseThrow();
        this.subscriptionsRepository.delete(subscriptionToDelete);
    }

    private SubscriptionsResponse entityToResponse(SubscriptionsEntity entity) {
        var response = new SubscriptionsResponse();
        BeanUtils.copyProperties(entity, response);

        var customerResponse = new CustomersResponse();
        BeanUtils.copyProperties(entity.getCustomer(), customerResponse);
        response.setCustomer(customerResponse);

        var typeResponse = new SubscriptionsTypesResponse();
        BeanUtils.copyProperties(entity.getType(), typeResponse);
        response.setType(typeResponse);

        return response;
    }

    @Override
    public HashSet<SubscriptionsResponse> readByCustomerId(UUID uuid) {
        var subscriptionsFromDB = this.subscriptionsRepository.findAllSubscriptionsByCustomerId(uuid);
        var subscriptionsForResponse = new HashSet<SubscriptionsResponse>();
        for(SubscriptionsEntity s : subscriptionsFromDB) {
            subscriptionsForResponse.add(this.entityToResponse(s));
        }

        return subscriptionsForResponse;
    }

    @Override
    public void deleteByCustomerId(UUID uuid) {
        this.subscriptionsRepository.deleteAllSubscriptionsByCustomerId(uuid);
    }

    @Override
    public HashSet<SubscriptionsResponse> readActiveByCustomer(UUID uuid) {
        var subscriptionsFromDB = this.subscriptionsRepository.findActiveSubscriptionsByCustomerId(uuid);
        var subscriptionsForResponse = new HashSet<SubscriptionsResponse>();
        for(SubscriptionsEntity s : subscriptionsFromDB) {
            subscriptionsForResponse.add(this.entityToResponse(s));
        }

        return subscriptionsForResponse;
    }

    @Override
    public void deleteExpiredByCustomer(UUID uuid) {
        this.subscriptionsRepository.deleteExpiredSubscriptionsByCustomerId(uuid);
    }

    @Override
    public void deleteAllExpired() {
        this.subscriptionsRepository.deleteExpiredSubscriptions();
    }

    @Override
    public void deleteAll() {
        this.subscriptionsRepository.deleteAll();
    }
}
