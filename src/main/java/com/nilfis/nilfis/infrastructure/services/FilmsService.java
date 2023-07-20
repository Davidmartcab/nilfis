package com.nilfis.nilfis.infrastructure.services;

import com.nilfis.nilfis.api.models.requests.FilmsRequest;
import com.nilfis.nilfis.api.models.responses.FilmsResponse;
import com.nilfis.nilfis.domain.entities.FilmsEntity;
import com.nilfis.nilfis.domain.repositories.FilmsRepository;
import com.nilfis.nilfis.infrastructure.abstract_service.IFilmsService;
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
@CacheConfig(cacheNames = CacheConstants.FILMS_CACHE_NAME)
public class FilmsService implements IFilmsService {

    private final FilmsRepository filmsRepository;
    @Override
    @CacheEvict(allEntries = true)
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
        var filmFromDB = this.filmsRepository.findById(uuid).orElseThrow(() -> new IdNotFoundException(Tables.films.name()));
        return this.entityToResponse(filmFromDB);
    }

    @Override
    public HashSet<FilmsResponse> read() {
        return null;
    }

    @Override
    @CacheEvict(allEntries = true)
    public void delete(UUID uuid) {
        var filmToDelete = this.filmsRepository.findById(uuid).orElseThrow(() -> new IdNotFoundException(Tables.films.name()));
        this.filmsRepository.delete(filmToDelete);
    }

    private FilmsResponse entityToResponse(FilmsEntity entity) {
        var response = new FilmsResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

}
