package com.nilfis.nilfis.infrastructure.services;

import com.nilfis.nilfis.api.models.requests.FilmsWatchedRequest;
import com.nilfis.nilfis.api.models.responses.*;
import com.nilfis.nilfis.domain.entities.CustomersEntity;
import com.nilfis.nilfis.domain.entities.FilmsEntity;
import com.nilfis.nilfis.domain.entities.FilmsWatchedEntity;
import com.nilfis.nilfis.domain.repositories.CustomersRepository;
import com.nilfis.nilfis.domain.repositories.FilmsRepository;
import com.nilfis.nilfis.domain.repositories.FilmsWatchedRepository;
import com.nilfis.nilfis.infrastructure.abstract_service.IFilmsWatchedService;
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
public class FlimsWatchedService implements IFilmsWatchedService {

    private final FilmsWatchedRepository filmsWatchedRepository;
    private final CustomersRepository customersRepository;
    private final FilmsRepository filmsRepository;
    @Override
    public FilmsWatchedResponse create(FilmsWatchedRequest request) {
        var customer = this.customersRepository.findById(request.getCustomer_id()).orElseThrow();
        var film = new FilmsEntity();
        try {
            film = this.filmsRepository.getReferenceById(request.getFilm_id());
        } catch (Exception e) {
            log.info("Error: film not found");
            return null;
        }
        var filmWToPersist = FilmsWatchedEntity.builder()
                .customer(customer)
                .film(film)
                .date(LocalDate.now())
                .build();
        var filmWPersisted = this.filmsWatchedRepository.save(filmWToPersist);
        return this.entityToResponse(filmWPersisted);
    }

    @Override
    public FilmsWatchedResponse read(UUID uuid) {
        var filmWFromDB = this.filmsWatchedRepository.findById(uuid).orElseThrow();
        return this.entityToResponse(filmWFromDB);
    }

    @Override
    public HashSet<FilmsWatchedResponse> read() {
        var filmWFromDB = this.filmsWatchedRepository.findAll();
        var filmWForResponse = new HashSet<FilmsWatchedResponse>();
        for (FilmsWatchedEntity film : filmWFromDB) {
            filmWForResponse.add(this.entityToResponse(film));
        }

        return filmWForResponse;
    }

    @Override
    public void delete(UUID uuid) {
        var filmWToDelete = this.filmsWatchedRepository.findById(uuid).orElseThrow();
        this.filmsWatchedRepository.delete(filmWToDelete);
    }

    @Override
    public CountFilmsResponse readByFilm(UUID uuid) {
        CountFilmsResponse response = new CountFilmsResponse();
        Object[] set = this.filmsWatchedRepository.countFilmWatchOccurrencesByFilmId(uuid);
        var film = new FilmsEntity();
        try {
            film = this.filmsRepository.getReferenceById(uuid);
        } catch (Exception e) {
            log.info("Error: film not found");
            return null;
        }
        if (set != null && set.length >= 1) {
            Long count = (Long) set[0];
            response.setCount(count);
        } else {
            response.setCount(-1);
        }
        response.setFilm(this.entityToResponse(film));
        return response;
    }

    @Override
    public FilmsByCustomerResponse readByCustomer(UUID uuid) {
        FilmsByCustomerResponse response = new FilmsByCustomerResponse();
        var customer = this.customersRepository.findById(uuid).orElseThrow();
        response.setCustomer(this.entityToResponse(customer));

        var filmWFromDB = this.filmsWatchedRepository.findFilmsWatchedByCustomer(uuid);
        var filmWForResponse = new HashSet<FilmsResponse>();
        for (FilmsEntity film : filmWFromDB) {
            filmWForResponse.add(this.entityToResponse(film));
        }
        response.setFilms(filmWForResponse);
        return response;
    }

    private FilmsResponse entityToResponse(FilmsEntity entity) {
        var response = new FilmsResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    private CustomersResponse entityToResponse(CustomersEntity entity) {
        var response = new CustomersResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    private FilmsWatchedResponse entityToResponse(FilmsWatchedEntity entity) {
        var response = new FilmsWatchedResponse();
        BeanUtils.copyProperties(entity, response);
        CustomersResponse customerResponse = new CustomersResponse();
        BeanUtils.copyProperties(entity.getCustomer(), customerResponse);
        response.setCustomer(customerResponse);

        FilmsResponse filmsResponse = new FilmsResponse();
        BeanUtils.copyProperties(entity.getFilm(), filmsResponse);
        response.setFilm(filmsResponse);

        return response;
    }
}
