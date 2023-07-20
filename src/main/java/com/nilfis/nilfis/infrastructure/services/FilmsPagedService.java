package com.nilfis.nilfis.infrastructure.services;

import com.nilfis.nilfis.api.models.requests.FilterRequest;
import com.nilfis.nilfis.api.models.responses.FilmsResponse;
import com.nilfis.nilfis.domain.entities.FilmsEntity;
import com.nilfis.nilfis.domain.repositories.FilmsRepository;
import com.nilfis.nilfis.infrastructure.abstract_service.IFilmsPagedService;
import com.nilfis.nilfis.util.enums.CacheConstants;
import com.nilfis.nilfis.util.enums.SortType;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Transactional
@Service
@AllArgsConstructor
@CacheConfig(cacheNames = CacheConstants.FILMS_CACHE_NAME)
public class FilmsPagedService implements IFilmsPagedService {

    private final FilmsRepository filmsRepository;
    @Override
    @Cacheable(key = "{ #page, #size, #sortType }")
    public Page<FilmsResponse> readAll(Integer page, Integer size, SortType sortType) {
        PageRequest pageRequest = null;
        switch (sortType) {
            case NONE -> pageRequest = PageRequest.of(page, size);
            case LOWER -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case UPPER -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }
        return this.filmsRepository.findAll(pageRequest).map(this::entityToResponse);
    }

    @Override
    @Cacheable(key = "{ #page, #size, #sortType, #request }")
    public Page<FilmsResponse> readAllFiltered(Integer page, Integer size, SortType sortType, FilterRequest request) {
        PageRequest pageRequest = null;
        switch (sortType) {
            case NONE -> pageRequest = PageRequest.of(page, size);
            case LOWER -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case UPPER -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }

        var films = this.filmsRepository.findAll();
        var response = new HashSet<FilmsResponse>();
        films.forEach(film -> {
            if(Objects.nonNull(request.getType_name())) {
                if(request.getType_name().equals("null")) {
                    if(Objects.nonNull(film.getSubscription_type_required())) {
                        return;
                    }
                }else {
                    if(Objects.isNull(film.getSubscription_type_required())) {
                        return;
                    }else {
                        if(!film.getSubscription_type_required().toLowerCase().contains(request.getType_name().toLowerCase())) {
                            return;
                        }
                    }
                }
            }

            if (Objects.nonNull(request.getMinRate())) {
                if (film.getRate() < request.getMinRate()) {
                    return;
                }
            }

            if (Objects.nonNull(request.getMaxRate())) {
                if (film.getRate() > request.getMaxRate()) {
                    return;
                }
            }

            if (Objects.nonNull(request.getTitleStart())) {
                if (!film.getTitle().toLowerCase().startsWith(request.getTitleStart().toLowerCase())) {
                    return;
                }
            }

            if (Objects.nonNull(request.getDirectorStart())) {
                if (!film.getDirector().toLowerCase().startsWith(request.getDirectorStart().toLowerCase())) {
                    return;
                }
            }

            if (Objects.nonNull(request.getYearStart())) {
                try {
                    int yearStart = Integer.parseInt(request.getYearStart());
                    if (Integer.parseInt(film.getYear()) < yearStart) {
                        return;
                    }
                } catch (Exception ignored) {}
            }

            if (Objects.nonNull(request.getYearEnd())) {
                try {
                    int yearEnd = Integer.parseInt(request.getYearEnd());
                    if (Integer.parseInt(film.getYear()) > yearEnd) {
                        return;
                    }
                } catch (Exception ignored) {}
            }

            response.add(this.entityToResponse(film));

        });

        List<FilmsResponse> responseList = new ArrayList<>(response);
        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), responseList.size());
        end = Math.max(start, end);
        try {
            return new PageImpl<>(responseList.subList(start, end), pageRequest, responseList.size());
        } catch (Exception e) {
            return new PageImpl<>(Collections.emptyList(), pageRequest, 0);
        }
    }

    private FilmsResponse entityToResponse(FilmsEntity entity) {
        FilmsResponse response = new FilmsResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

}
