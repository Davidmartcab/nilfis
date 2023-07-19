package com.nilfis.nilfis.infrastructure.services;

import com.nilfis.nilfis.api.models.requests.FilterRequest;
import com.nilfis.nilfis.api.models.responses.FilmsResponse;
import com.nilfis.nilfis.api.models.responses.SeriesResponse;
import com.nilfis.nilfis.domain.entities.SeriesEntity;
import com.nilfis.nilfis.domain.repositories.SeriesRepository;
import com.nilfis.nilfis.infrastructure.abstract_service.ISeriesPagedService;
import com.nilfis.nilfis.util.SortType;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Transactional
@Service
@AllArgsConstructor
public class SeriesPagedService implements ISeriesPagedService {

    private final SeriesRepository seriesRepository;
    @Override
    public Page<SeriesResponse> readAll(Integer page, Integer size, SortType sortType) {
        PageRequest pageRequest = null;
        switch (sortType) {
            case NONE -> pageRequest = PageRequest.of(page, size);
            case LOWER -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case UPPER -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }
        return this.seriesRepository.findAll(pageRequest).map(this::entityToResponse);
    }

    @Override
    public Page<SeriesResponse> readAllFiltered(Integer page, Integer size, SortType sortType, FilterRequest request) {
        PageRequest pageRequest = null;
        switch (sortType) {
            case NONE -> pageRequest = PageRequest.of(page, size);
            case LOWER -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case UPPER -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }

        var films = this.seriesRepository.findAll();
        var response = new HashSet<SeriesResponse>();
        films.forEach(film -> {
            boolean valid = true;
            if(Objects.nonNull(request.getType_name())) {
                if(request.getType_name().equals("null")) {
                    if(Objects.nonNull(film.getSubscription_type_required())) {
                        valid = false;
                        return;
                    }
                }else {
                    if(Objects.isNull(film.getSubscription_type_required())) {
                        valid = false;
                        return;
                    }else {
                        if(!film.getSubscription_type_required().toLowerCase().contains(request.getType_name().toLowerCase())) {
                            valid = false;
                            return;
                        }
                    }
                }
            }

            if (Objects.nonNull(request.getMinRate())) {
                if (film.getRate() < request.getMinRate()) {
                    valid = false;
                    return;
                }
            }

            if (Objects.nonNull(request.getMaxRate())) {
                if (film.getRate() > request.getMaxRate()) {
                    valid = false;
                    return;
                }
            }

            if (Objects.nonNull(request.getTitleStart())) {
                if (!film.getTitle().toLowerCase().startsWith(request.getTitleStart().toLowerCase())) {
                    valid = false;
                    return;
                }
            }

            if (Objects.nonNull(request.getDirectorStart())) {
                if (!film.getDirector().toLowerCase().startsWith(request.getDirectorStart().toLowerCase())) {
                    valid = false;
                    return;
                }
            }

            if (Objects.nonNull(request.getYearStart())) {
                try {
                    int yearStart = Integer.parseInt(request.getYearStart());
                    if (Integer.parseInt(film.getYear()) < yearStart) {
                        valid = false;
                        return;
                    }
                } catch (Exception ignored) {}
            }

            if (Objects.nonNull(request.getYearEnd())) {
                try {
                    int yearEnd = Integer.parseInt(request.getYearEnd());
                    if (Integer.parseInt(film.getYear()) > yearEnd) {
                        valid = false;
                        return;
                    }
                } catch (Exception ignored) {}
            }

            if (valid) {
                response.add(this.entityToResponse(film));
            }

        });

        List<SeriesResponse> responseList = new ArrayList<>(response);
        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), responseList.size());
        end = Math.max(start, end);
        return new PageImpl<>(responseList.subList(start, end), pageRequest, responseList.size());
    }

    private SeriesResponse entityToResponse(SeriesEntity entity) {
        SeriesResponse response = new SeriesResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }
}
