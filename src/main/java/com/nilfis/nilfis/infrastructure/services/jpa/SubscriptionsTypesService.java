package com.nilfis.nilfis.infrastructure.services.jpa;

import com.nilfis.nilfis.api.models.requests.jpa.SubscriptionsTypesRequest;
import com.nilfis.nilfis.api.models.responses.jpa.SubscriptionsTypesResponse;
import com.nilfis.nilfis.domain.entities.jpa.SubscriptionsTypesEntity;
import com.nilfis.nilfis.domain.repositories.jpa.SubscriptionsTypesRepository;
import com.nilfis.nilfis.infrastructure.abstract_service.jpa.ISubscriptionsTypesService;
import com.nilfis.nilfis.util.enums.CacheConstants;
import com.nilfis.nilfis.util.enums.Tables;
import com.nilfis.nilfis.util.exceptions.IdNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.UUID;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
@CacheConfig(cacheNames = CacheConstants.SUBSCRIPTIONS_TYPES_CACHE_NAME)
public class SubscriptionsTypesService implements ISubscriptionsTypesService {

    private final SubscriptionsTypesRepository subscriptionsTypesRepository;
    @Override
    @CacheEvict(allEntries = true)
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
        var sbsTypesFromDB = this.subscriptionsTypesRepository.findById(uuid).orElseThrow(() -> new IdNotFoundException(Tables.subscriptions_types.name()));
        return this.entityToResponse(sbsTypesFromDB);
    }

    @Override
    @CacheEvict(allEntries = true)
    public void delete(UUID uuid) {
        var customerToDelete = this.subscriptionsTypesRepository.findById(uuid).orElseThrow(() -> new IdNotFoundException(Tables.subscriptions_types.name()));
        this.subscriptionsTypesRepository.delete(customerToDelete);
    }

    @Override
    @Cacheable()
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
