package com.nilfis.nilfis.infrastructure.services.jpa;

import com.nilfis.nilfis.api.models.requests.jpa.SeriesRequest;
import com.nilfis.nilfis.api.models.responses.jpa.SeriesResponse;
import com.nilfis.nilfis.domain.entities.jpa.SeriesEntity;
import com.nilfis.nilfis.domain.repositories.jpa.SeriesRepository;
import com.nilfis.nilfis.infrastructure.abstract_service.jpa.ISeriesService;
import com.nilfis.nilfis.util.enums.CacheConstants;
import com.nilfis.nilfis.util.enums.Tables;
import com.nilfis.nilfis.util.exceptions.IdNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.UUID;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
@CacheConfig(cacheNames = CacheConstants.SERIES_CACHE_NAME)
public class SeriesService implements ISeriesService {

    private final SeriesRepository seriesRepository;

    @Override
    @CacheEvict(allEntries = true)
    public SeriesResponse create(SeriesRequest request) {
        var seriesToPersist = SeriesEntity.builder()
                .title(request.getTitle().toLowerCase())
                .director(request.getDirector().toLowerCase())
                .url(request.getUrl())
                .rate(request.getRate())
                .chapters(request.getChapters())
                .subscription_type_required(request.getSubscription_type_required().toLowerCase())
                .build();
        var seriesPersisted = this.seriesRepository.save(seriesToPersist);
        return this.entityToResponse(seriesPersisted);
    }

    @Override
    public SeriesResponse read(UUID uuid) {
        var seriesFromDB = this.seriesRepository.findById(uuid).orElseThrow(() -> new IdNotFoundException(Tables.series.name()));
        return this.entityToResponse(seriesFromDB);
    }

    @Override
    public HashSet<SeriesResponse> read() {
        return null;
    }

    @Override
    @CacheEvict(allEntries = true)
    public void delete(UUID uuid) {
        var seriesToDelete = this.seriesRepository.findById(uuid).orElseThrow(() -> new IdNotFoundException(Tables.series.name()));
        this.seriesRepository.delete(seriesToDelete);
    }

    private SeriesResponse entityToResponse(SeriesEntity entity) {
        var response = new SeriesResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

}
