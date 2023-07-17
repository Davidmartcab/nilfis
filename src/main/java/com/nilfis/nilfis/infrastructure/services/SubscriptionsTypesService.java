package com.nilfis.nilfis.infrastructure.services;

import com.nilfis.nilfis.api.models.requests.SubscriptionsTypesRequest;
import com.nilfis.nilfis.api.models.responses.SubscriptionsTypesResponse;
import com.nilfis.nilfis.domain.entities.CustomersEntity;
import com.nilfis.nilfis.domain.entities.SubscriptionsTypesEntity;
import com.nilfis.nilfis.domain.repositories.SubscriptionsTypesRepository;
import com.nilfis.nilfis.infrastructure.abstract_service.ISubscriptionsTypesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.UUID;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class SubscriptionsTypesService implements ISubscriptionsTypesService {

    private final SubscriptionsTypesRepository subscriptionsTypesRepository;
    @Override
    public SubscriptionsTypesResponse create(SubscriptionsTypesRequest request) {
        var sbsTypeToPersist = SubscriptionsTypesEntity.builder()
                .name(request.getName().toLowerCase())
                .price(request.getPrice())
                .duration(request.getDuration())
                .build();
        var sbsTypePersisted = this.subscriptionsTypesRepository.save(sbsTypeToPersist);
        return this.entityToResponse(sbsTypePersisted);
    }

    @Override
    public SubscriptionsTypesResponse read(UUID uuid) {
        var sbsTypesFromDB = this.subscriptionsTypesRepository.findById(uuid).orElseThrow();
        return this.entityToResponse(sbsTypesFromDB);
    }

    @Override
    public void delete(UUID uuid) {
        var customerToDelete = this.subscriptionsTypesRepository.findById(uuid).orElseThrow();
        this.subscriptionsTypesRepository.delete(customerToDelete);
    }

    @Override
    public HashSet<SubscriptionsTypesResponse> read() {
        var sbsTypesFromDB = this.subscriptionsTypesRepository.findAll();
        var sbsTypesForResponse = new HashSet<SubscriptionsTypesResponse>();
        for (SubscriptionsTypesEntity sbsType : sbsTypesFromDB) {
            sbsTypesForResponse.add(this.entityToResponse(sbsType));
        }

        return sbsTypesForResponse;
    }

    private SubscriptionsTypesResponse entityToResponse(SubscriptionsTypesEntity entity) {
        var response = new SubscriptionsTypesResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }
}
