package com.nilfis.nilfis.infrastructure.abstract_service.jpa;

import com.nilfis.nilfis.api.models.requests.jpa.FilterRequest;
import com.nilfis.nilfis.util.enums.SortType;
import org.springframework.data.domain.Page;

public interface CatalogService<R> {
    Page<R> readAll(Integer page, Integer size, SortType sortType);

    Page<R> readAllFiltered(Integer page, Integer size, SortType sortType, FilterRequest request);

    String FIELD_BY_SORT = "title";
}
