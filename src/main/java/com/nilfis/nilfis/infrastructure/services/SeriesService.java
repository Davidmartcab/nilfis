package com.nilfis.nilfis.infrastructure.services;

import com.nilfis.nilfis.api.models.requests.SeriesRequest;
import com.nilfis.nilfis.api.models.responses.SeriesResponse;
import com.nilfis.nilfis.domain.entities.SeriesEntity;
import com.nilfis.nilfis.domain.repositories.SeriesRepository;
import com.nilfis.nilfis.infrastructure.abstract_service.ISeriesService;
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
        var seriesFromDB = this.seriesRepository.findById(uuid).orElseThrow();
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
        var seriesToDelete = this.seriesRepository.findById(uuid).orElseThrow();
        this.seriesRepository.delete(seriesToDelete);
    }

    private SeriesResponse entityToResponse(SeriesEntity entity) {
        var response = new SeriesResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    @Override
    public HashSet<SeriesResponse> readBySubscriptionsType(String typeName) {
        var seriesFromDB = this.seriesRepository.findBySubscriptionName(typeName);
        var seriesForResponse = new HashSet<SeriesResponse>();
        for (SeriesEntity series : seriesFromDB) {
            seriesForResponse.add(this.entityToResponse(series));
        }
        return seriesForResponse;
    }

    @Override
    public HashSet<SeriesResponse> readByRateGreater(int rate) {
        var seriesFromDB = this.seriesRepository.findByRateGreaterThan(rate);
        var seriesForResponse = new HashSet<SeriesResponse>();
        for (SeriesEntity series : seriesFromDB) {
            seriesForResponse.add(this.entityToResponse(series));
        }
        return seriesForResponse;
    }

    @Override
    public HashSet<SeriesResponse> readByRateLower(int rate) {
        var seriesFromDB = this.seriesRepository.findByRateLessThan(rate);
        var seriesForResponse = new HashSet<SeriesResponse>();
        for (SeriesEntity series : seriesFromDB) {
            seriesForResponse.add(this.entityToResponse(series));
        }
        return seriesForResponse;
    }

    @Override
    public HashSet<SeriesResponse> readByRateBetween(int minRate, int maxRate) {
        var seriesFromDB = this.seriesRepository.findByRateBetween(minRate, maxRate);
        var seriesForResponse = new HashSet<SeriesResponse>();
        for (SeriesEntity series : seriesFromDB) {
            seriesForResponse.add(this.entityToResponse(series));
        }
        return seriesForResponse;
    }

    @Override
    public HashSet<SeriesResponse> readByTitleStart(String titleStart) {
        var seriesFromDB = this.seriesRepository.findByTitleStartingWith(titleStart);
        var seriesForResponse = new HashSet<SeriesResponse>();
        for (SeriesEntity series : seriesFromDB) {
            seriesForResponse.add(this.entityToResponse(series));
        }
        return seriesForResponse;
    }

    @Override
    public HashSet<SeriesResponse> readByDirectorStart(String directorStart) {
        var seriesFromDB = this.seriesRepository.findByDirectorStartingWith(directorStart);
        var seriesForResponse = new HashSet<SeriesResponse>();
        for (SeriesEntity series : seriesFromDB) {
            seriesForResponse.add(this.entityToResponse(series));
        }
        return seriesForResponse;
    }

    @Override
    public HashSet<SeriesResponse> readByYear(String startYear, String endYear) {
        var seriesFromDB = this.seriesRepository.findByYearInRange(startYear, endYear);
        var seriesForResponse = new HashSet<SeriesResponse>();
        for (SeriesEntity series : seriesFromDB) {
            seriesForResponse.add(this.entityToResponse(series));
        }
        return seriesForResponse;
    }
}
