package com.nilfis.nilfis.infrastructure.services;

import com.nilfis.nilfis.api.models.requests.SeriesRequest;
import com.nilfis.nilfis.api.models.responses.SeriesResponse;
import com.nilfis.nilfis.domain.entities.SeriesEntity;
import com.nilfis.nilfis.domain.repositories.SeriesRepository;
import com.nilfis.nilfis.infrastructure.abstract_service.ISeriesService;
import com.nilfis.nilfis.util.enums.Tables;
import com.nilfis.nilfis.util.exceptions.IdNotFoundException;
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
public class SeriesService implements ISeriesService {

    private final SeriesRepository seriesRepository;

    @Override
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
        var seriesFromDB = this.seriesRepository.findAll();
        var seriesForResponse = new HashSet<SeriesResponse>();
        for (SeriesEntity series : seriesFromDB) {
            seriesForResponse.add(this.entityToResponse(series));
        }
        return seriesForResponse;
    }

    @Override
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
