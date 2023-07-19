package com.nilfis.nilfis.infrastructure.abstract_service;

import com.nilfis.nilfis.api.models.requests.FilterRequest;
import com.nilfis.nilfis.util.SortType;
import org.springframework.data.domain.Page;

import java.util.HashSet;

public interface CatalogService<R> {
    Page<R> readAll(Integer page, Integer size, SortType sortType);

    Page<R> readAllFiltered(Integer page, Integer size, SortType sortType, FilterRequest request);

    String FIELD_BY_SORT = "title";
}
