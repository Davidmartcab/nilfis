package com.nilfis.nilfis.infrastructure.services;

import com.nilfis.nilfis.api.models.requests.SeriesWatchedRequest;
import com.nilfis.nilfis.api.models.responses.*;
import com.nilfis.nilfis.domain.entities.*;
import com.nilfis.nilfis.domain.repositories.*;
import com.nilfis.nilfis.infrastructure.abstract_service.ISeriesWatchedService;
import com.nilfis.nilfis.util.enums.Tables;
import com.nilfis.nilfis.util.exceptions.IdNotFoundException;
import com.nilfis.nilfis.util.exceptions.NotVerifiedCustomer;
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
public class SeriesWatchedService implements ISeriesWatchedService {

    private final SeriesWatchedRepository seriesWatchedRepository;
    private final CustomersRepository customersRepository;
    private final SeriesRepository seriesRepository;

    @Override
    public SeriesWatchedResponse create(SeriesWatchedRequest request) {
        var customer = this.customersRepository.findById(request.getCustomer_id()).orElseThrow(() -> new IdNotFoundException(Tables.customers.name()));
        if(!customer.isVerified()){throw new NotVerifiedCustomer();}
        var serie = new SeriesEntity();
        try {
            serie = this.seriesRepository.getReferenceById(request.getSerie_id());
        } catch (Exception e) {
            throw new IdNotFoundException(Tables.series.name());
        }
        var filmWToPersist = SeriesWatchedEntity.builder()
                .customer(customer)
                .serie(serie)
                .date(LocalDate.now())
                .build();
        var filmWPersisted = this.seriesWatchedRepository.save(filmWToPersist);
        return this.entityToResponse(filmWPersisted);
    }

    @Override
    public SeriesWatchedResponse read(UUID uuid) {
        var serieWFromDB = this.seriesWatchedRepository.findById(uuid).orElseThrow();
        return this.entityToResponse(serieWFromDB);
    }

    @Override
    public HashSet<SeriesWatchedResponse> read() {
        var serieWFromDB = this.seriesWatchedRepository.findAll();
        var serieWForResponse = new HashSet<SeriesWatchedResponse>();
        for (SeriesWatchedEntity serie : serieWFromDB) {
            serieWForResponse.add(this.entityToResponse(serie));
        }

        return serieWForResponse;
    }

    @Override
    public void delete(UUID uuid) {
        var serieWToDelete = this.seriesWatchedRepository.findById(uuid).orElseThrow(() -> new IdNotFoundException(Tables.series_watched.name()));
        this.seriesWatchedRepository.delete(serieWToDelete);
    }

    @Override
    public CountSeriesResponse readBySerie(UUID uuid) {
        CountSeriesResponse response = new CountSeriesResponse();
        Object[] set = this.seriesWatchedRepository.countSeriesWatchOccurrencesBySeriesId(uuid);
        var series = new SeriesEntity();

        try {
            series = this.seriesRepository.getReferenceById(uuid);
        } catch (Exception e) {
            throw new IdNotFoundException(Tables.series.name());
        }

        if (set != null && set.length >= 1) {
            Long count = (Long) set[0];
            response.setCount(count);
        } else {
            response.setCount(-1L);
        }

        response.setSerie(this.entityToResponse(series));
        return response;

    }

    @Override
    public SeriesByCustomerResponse readByCustomer(UUID uuid) {
        SeriesByCustomerResponse response = new SeriesByCustomerResponse();
        var customer = this.customersRepository.findById(uuid).orElseThrow(() -> new IdNotFoundException(Tables.customers.name()));
        response.setCustomer(this.entityToResponse(customer));

        var seriesWFromDB = this.seriesWatchedRepository.findSeriesWatchedByCustomer(uuid);
        var seriesWForResponse = new HashSet<SeriesResponse>();
        for (SeriesEntity series : seriesWFromDB) {
            seriesWForResponse.add(this.entityToResponse(series));
        }
        response.setSeries(seriesWForResponse);
        return response;
    }

    private SeriesResponse entityToResponse(SeriesEntity entity) {
        var response = new SeriesResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    private CustomersResponse entityToResponse(CustomersEntity entity) {
        var response = new CustomersResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    private SeriesWatchedResponse entityToResponse(SeriesWatchedEntity entity) {
        var response = new SeriesWatchedResponse();
        BeanUtils.copyProperties(entity, response);
        CustomersResponse customerResponse = new CustomersResponse();
        BeanUtils.copyProperties(entity.getCustomer(), customerResponse);
        response.setCustomer(customerResponse);

        SeriesResponse seriesResponse = new SeriesResponse();
        BeanUtils.copyProperties(entity.getSerie(), seriesResponse);
        response.setSerie(seriesResponse);

        return response;
    }
}
