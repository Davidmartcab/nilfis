package com.nilfis.nilfis.infrastructure.services;

import com.nilfis.nilfis.api.models.requests.FilmsRequest;
import com.nilfis.nilfis.api.models.responses.FilmsResponse;
import com.nilfis.nilfis.domain.entities.FilmsEntity;
import com.nilfis.nilfis.domain.repositories.FilmsRepository;
import com.nilfis.nilfis.infrastructure.abstract_service.IFilmsService;
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
public class FilmsService implements IFilmsService {

    private final FilmsRepository filmsRepository;
    @Override
    public FilmsResponse create(FilmsRequest request) {
        var filmToPersist = FilmsEntity.builder()
                .title(request.getTitle().toLowerCase())
                .director(request.getDirector().toLowerCase())
                .year(request.getYear())
                .url(request.getUrl())
                .rate(request.getRate())
                .duration(request.getDuration())
                .subscription_type_required(request.getSubscription_type_required().toLowerCase())
                .build();
        var filmPersisted = this.filmsRepository.save(filmToPersist);
        return this.entityToResponse(filmPersisted);
    }

    @Override
    public FilmsResponse read(UUID uuid) {
        var filmFromDB = this.filmsRepository.findById(uuid).orElseThrow();
        return this.entityToResponse(filmFromDB);
    }

    @Override
    public HashSet<FilmsResponse> read() {
        var filmFromDB = this.filmsRepository.findAll();
        var filmForResponse = new HashSet<FilmsResponse>();
        for (FilmsEntity film : filmFromDB) {
            filmForResponse.add(this.entityToResponse(film));
        }

        return filmForResponse;
    }

    @Override
    public void delete(UUID uuid) {
        var filmToDelete = this.filmsRepository.findById(uuid).orElseThrow();
        this.filmsRepository.delete(filmToDelete);
    }

    private FilmsResponse entityToResponse(FilmsEntity entity) {
        var response = new FilmsResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    @Override
    public HashSet<FilmsResponse> readBySubscriptionsType(String typeName) {
        var filmFromDB = this.filmsRepository.findBySubscriptionName(typeName);
        var filmForResponse = new HashSet<FilmsResponse>();
        for (FilmsEntity film : filmFromDB) {
            filmForResponse.add(this.entityToResponse(film));
        }

        return filmForResponse;
    }

    @Override
    public HashSet<FilmsResponse> readByRateGreater(int rate) {
        var filmFromDB = this.filmsRepository.findByRateGreaterThan(rate);
        var filmForResponse = new HashSet<FilmsResponse>();
        for (FilmsEntity film : filmFromDB) {
            filmForResponse.add(this.entityToResponse(film));
        }

        return filmForResponse;
    }

    @Override
    public HashSet<FilmsResponse> readByRateLower(int rate) {
        var filmFromDB = this.filmsRepository.findByRateLessThan(rate);
        var filmForResponse = new HashSet<FilmsResponse>();
        for (FilmsEntity film : filmFromDB) {
            filmForResponse.add(this.entityToResponse(film));
        }

        return filmForResponse;
    }

    @Override
    public HashSet<FilmsResponse> readByRateBetween(int minRate, int maxRate) {
        var filmFromDB = this.filmsRepository.findByRateBetween(minRate, maxRate);
        var filmForResponse = new HashSet<FilmsResponse>();
        for (FilmsEntity film : filmFromDB) {
            filmForResponse.add(this.entityToResponse(film));
        }

        return filmForResponse;
    }

    @Override
    public HashSet<FilmsResponse> readByTitleStart(String titleStart) {
        var filmFromDB = this.filmsRepository.findByTitleStartingWith(titleStart);
        var filmForResponse = new HashSet<FilmsResponse>();
        for (FilmsEntity film : filmFromDB) {
            filmForResponse.add(this.entityToResponse(film));
        }

        return filmForResponse;
    }

    @Override
    public HashSet<FilmsResponse> readByDirectorStart(String directorStart) {
        var filmFromDB = this.filmsRepository.findByDirectorStartingWith(directorStart);
        var filmForResponse = new HashSet<FilmsResponse>();
        for (FilmsEntity film : filmFromDB) {
            filmForResponse.add(this.entityToResponse(film));
        }

        return filmForResponse;
    }

    @Override
    public HashSet<FilmsResponse> readByYear(String startYear, String endYear) {
        var filmFromDB = this.filmsRepository.findByYearInRange(startYear, endYear);
        var filmForResponse = new HashSet<FilmsResponse>();
        for (FilmsEntity film : filmFromDB) {
            filmForResponse.add(this.entityToResponse(film));
        }

        return filmForResponse;
    }
}
